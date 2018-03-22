package com.paxsz.service.impl;

import com.paxsz.bean.BaseDict;
import com.paxsz.dao.BaseDictDao;
import com.paxsz.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {

    private BaseDictDao bdd;

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return bdd.getListByTypeCode(dict_type_code);
    }

    public void setBdd(BaseDictDao bdd) {
        this.bdd = bdd;
    }
}
