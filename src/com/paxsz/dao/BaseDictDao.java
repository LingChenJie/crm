package com.paxsz.dao;

import com.paxsz.bean.BaseDict;

import java.util.List;

public interface BaseDictDao extends BaseDao<BaseDictDao> {

    /**
     * 根据类型获得数据字典列表
     *
     * @param dict_type_code
     * @return
     */
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
