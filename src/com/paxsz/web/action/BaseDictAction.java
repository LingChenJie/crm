package com.paxsz.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.paxsz.bean.BaseDict;
import com.paxsz.service.BaseDictService;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class BaseDictAction extends ActionSupport {

    private String dict_type_code;

    private BaseDictService baseDictService;

    @Override
    public String execute() throws Exception {

        // 调用Service根据typecode获得数据字典对象list
        List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);

        // 转为json格式
        String json = JSONArray.fromObject(list).toString();

        // 将json发送给浏览器
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);

        return null;//告诉struts2不需要进行结果处理
    }

    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
}
