/**
 * 使用ajax加载数据字典，生成select
 * @param typecode 数据字典类型(dict_type_code)
 * @param positionId 将下拉选放入的标签id
 * @param selectname 生成下拉选时，select标签的name属性值
 * @param selectedId 需要回显时，选中哪个option
 */
function loadSelect(typecode, positionId, selectname, selectedId) {

    // 1.创建select对象，将name属性指定
    var $select = $("<select name=" + selectname + "></select>");

    // 2.添加提示选项
    $select.append($("<option value=''>---请选择---</option>"));

    // 3.使用jquery的ajax方法，访问后台action
    $.post("${pageContext.request.contextPath}/BaseDictAction", {dict_type_code: typecode},
        function (data) {
            // 4.返回json数组对象，对其遍历
            $.each(data, function (i, json) {
                // 每次遍历创建一个option对象
                var $option = $("<option value='" + json['dict_id'] + "'>" + json["dict_item_name"] + "</option>");

                if (json['dict_id'] == selectedId) {
                    // 判断是否需要回显，如果需要使其被选中
                    $option.attr("selected", "selected");
                }

                // 添加到select对象中
                $select.append($option);
            });
        }, "json");

    // 5.将组装好的select对象放入页面指定位置
    $("#" + positionId).append($select);
}