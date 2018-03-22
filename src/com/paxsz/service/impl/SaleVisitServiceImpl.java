package com.paxsz.service.impl;

import com.paxsz.bean.SaleVisit;
import com.paxsz.dao.SaleVisitDao;
import com.paxsz.service.SaleVisitService;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class SaleVisitServiceImpl implements SaleVisitService {

    private SaleVisitDao svd;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void save(SaleVisit saleVisit) {
        svd.saveOrUpdate(saleVisit);
    }

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 1.调用Dao查询总记录数
        Integer count = svd.getTotalCount(dc);
        // 2.创建PageBean对象
        PageBean pb = new PageBean(currentPage, count, pageSize);
        // 3.调用Dao查询分页列表数据
        List<SaleVisit> list = svd.getPageList(dc, pb.getStart(), pb.getPageSize());
        // 4.列表数据放入PageBean中
        pb.setList(list);
        return pb;
    }

    @Override
    public SaleVisit getById(String visit_id) {
        return svd.getById(visit_id);
    }

    public void setSvd(SaleVisitDao svd) {
        this.svd = svd;
    }
}
