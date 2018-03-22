package com.paxsz.service.impl;

import com.paxsz.bean.Customer;
import com.paxsz.dao.CustomerDao;
import com.paxsz.service.CustomerService;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao cd;

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        Integer totalCount = cd.getTotalCount(dc);
        PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);
        List<Customer> list = cd.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Customer customer) {
        // 1 维护Customer与数据字典对象的关系,由于struts2参数封装,会将参数封装到数据字典的id属性.
        // 那么我们无需手动维护关系
        // 2 调用Dao保存客户
        cd.saveOrUpdate(customer);
    }

    @Override
    public Customer getById(Long cust_id) {
        Customer customer = cd.getById(cust_id);
        return customer;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long cust_id) {
        cd.delete(cust_id);
    }

    @Override
    public List<Object[]> getIndustryCount() {
        return cd.getIndustryCount();
    }

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}
