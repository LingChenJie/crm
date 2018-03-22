package com.paxsz.dao;

import com.paxsz.bean.Customer;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {

    /**
     * 按照行业统计客户数量
     *
     * @return
     */
    List<Object[]> getIndustryCount();
}
