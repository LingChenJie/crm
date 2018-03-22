package com.paxsz.service.impl;

import com.paxsz.bean.LinkMan;
import com.paxsz.dao.LinkManDao;
import com.paxsz.service.LinkManService;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class LinkManServiceImpl implements LinkManService {

    private LinkManDao lmd;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(LinkMan linkMan) {
        lmd.saveOrUpdate(linkMan);
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 1.调用dao查询总记录数
        Integer totalCount = lmd.getTotalCount(dc);
        // 2.创建PageBean对象
        PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);
        // 3.调用dao查询分页列表数据
        List<LinkMan> list = lmd.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
        // 4.把列表数据放入PageBean中，并返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public LinkMan getById(Long lkm_id) {
        return lmd.getById(lkm_id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteById(Long lkm_id) {
        lmd.delete(lkm_id);
    }

    public void setLmd(LinkManDao lmd) {
        this.lmd = lmd;
    }
}
