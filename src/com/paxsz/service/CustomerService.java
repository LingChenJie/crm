package com.paxsz.service;

import com.paxsz.bean.Customer;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

    /**
     * 分页业务方法
     *
     * @param dc
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    /**
     * 保存客户方法
     *
     * @param customer
     */
    void save(Customer customer);

    /**
     * 根据cust_id获取客户对象
     *
     * @param cust_id
     * @return
     */
    Customer getById(Long cust_id);

    /**
     * 根据cust_id删除客户对象
     *
     * @param cust_id
     */
    void delete(Long cust_id);

    /**
     * 获得按行业统计客户数量
     *
     * @return
     */
    List<Object[]> getIndustryCount();
}
