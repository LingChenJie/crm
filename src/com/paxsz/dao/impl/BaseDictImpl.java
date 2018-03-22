package com.paxsz.dao.impl;

import com.paxsz.bean.BaseDict;
import com.paxsz.dao.BaseDictDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BaseDictImpl extends BaseDaoImpl<BaseDictDao> implements BaseDictDao {

    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        // 创建离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);

        // 封装条件
        dc.add(Restrictions.eq("dict_type_code", dict_type_code));

        // 执行查询
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);

        return list;
    }

}
