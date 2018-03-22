package com.paxsz.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.paxsz.bean.SaleVisit;
import com.paxsz.bean.User;
import com.paxsz.service.SaleVisitService;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    private SaleVisit saleVisit = new SaleVisit();

    private SaleVisitService svs;
    private Integer currentPage;
    private Integer pageSize;


    public String add() throws Exception {
        // 1.取出登录用户，放入SaleVisit实体，表达关系
        User u = (User) ActionContext.getContext().getSession().get("user");
        saleVisit.setUser(u);
        // 2.调用Service保存客户拜访记录
        try {
            svs.save(saleVisit);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3.重定向到拜访记录列表Action
        return "toList";
    }

    public String list() throws Exception {
        // 封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
        // 判断并封装参数
        if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
            dc.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
        }

        // 1.调用Service查询分页数据
        try {
            PageBean pageBean = svs.getPageBean(dc, currentPage, pageSize);
            // 2.将pageBean放入request域，转发到列表页面显示
            ActionContext.getContext().put("pageBean", pageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "list";
    }

    public String toEdit() throws Exception {
        // 1.通过id查询到SaleVisit对象
        SaleVisit s = svs.getById(saleVisit.getVisit_id());
        // 2.把对象放入到request域中
        ActionContext.getContext().put("saleVisit", s);
        // 3.转发到编辑页面
        return "edit";
    }

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    public void setSvs(SaleVisitService svs) {
        this.svs = svs;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
