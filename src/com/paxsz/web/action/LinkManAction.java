package com.paxsz.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.paxsz.bean.LinkMan;
import com.paxsz.service.LinkManService;
import com.paxsz.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    private LinkMan linkMan = new LinkMan();

    private LinkManService lks;

    private Integer currentPage;
    private Integer pageSize;

    public String list() throws Exception {
        // 封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
        // 判断并封装参数
        if (StringUtils.isNotBlank(linkMan.getLkm_name())) {
            dc.add(Restrictions.like("lkm_name", "%" + linkMan.getLkm_name() + "%"));
        }
        if (linkMan.getCustomer() != null && linkMan.getCustomer().getCust_id() != null) {
            dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
        }

        // 1.调用Service查询分页数据
        PageBean pageBean = lks.getPageBean(dc, currentPage, pageSize);
        // 2.将pageBean放入request域，转发到列表页面显示
        ActionContext.getContext().put("pageBean", pageBean);
        return "list";
    }

    public String add() throws Exception {
        // 1.调用Service
        try {
            lks.save(linkMan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.重定向到联系人列表
        return "toList";
    }

    public String toEdit() throws Exception {
        // 1.调用Service,查询LinkMan
        LinkMan lm = lks.getById(linkMan.getLkm_id());
        // 2.将查询的LinkMan对象放入request域，转发到编辑页面
        ActionContext.getContext().put("linkMan", lm);
        return "edit";
    }

    public String delete() throws Exception {
        // 1.调用Service,删除LinkMan
        lks.deleteById(linkMan.getLkm_id());
        return "toList";
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    public void setLks(LinkManService lks) {
        this.lks = lks;
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
