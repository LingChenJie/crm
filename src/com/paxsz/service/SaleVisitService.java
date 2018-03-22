package com.paxsz.service;

import com.paxsz.bean.SaleVisit;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService {

    /**
     * 保存客户拜访记录
     *
     * @param saleVisit
     */
    void save(SaleVisit saleVisit);

    /**
     * 客户拜访记录的分页记录
     *
     * @param dc
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    /**
     * 根据id获得客户对象
     *
     * @param visit_id
     * @return
     */
    SaleVisit getById(String visit_id);
}
