package com.paxsz.service;

import com.paxsz.bean.BaseDict;

import java.util.List;

public interface BaseDictService {

    /**
     * 根据数据字典字段获得数据字典对象
     *
     * @param dict_type_code
     * @return
     */
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
