package com.paxsz.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.paxsz.bean.Customer;
import com.paxsz.service.CustomerService;
import com.paxsz.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();
    private CustomerService cs;

    // 上传的文件会自动封装到File对象
    // 在后台提供一个与前台input type=file组件 name相同的属性
    private File photo;
    // 在提交键名后加上固定后缀FileName,文件名称会自动封装到属性中
    private String photoFileName;
    // 在提交键名后加上固定后缀ContentType，文件MIME类型会自动封装到属性中
    private String photoContentType;

    private Integer currentPage;// 当前页码
    private Integer pageSize;// 每页显示条数

    public String list() {
        // 封装离线查询对象
        DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
        // 判断参数并封装参数
        if (StringUtils.isNotBlank(customer.getCust_name())) {
            dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }

        // 调用分页查询数据
        PageBean pageBean = cs.getPageBean(dc, currentPage, pageSize);

        // 将pageBean放入request域中，转发到列表页面中显示
        ActionContext.getContext().put("pageBean", pageBean);
        return "list";
    }

    public String add() {

        if (photo != null) {
            System.out.println("文件名称：" + photoFileName);
            System.out.println("文件类型：" + photoContentType);
            // 将文件保存到指定位置
            photo.renameTo(new File("E:/upload/susu.jpg"));
        }


        //---------------------------------------------------------------
        // 1.调用Service,保存Customer对象
        try {
            cs.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2.重定向到客户列表Action
        return "toList";
    }

    public String toEdit() {
        // 1.调用Service获取客户对象
        Customer cust = cs.getById(customer.getCust_id());
        // 2.将客户对象放置到request域中，并转发到编辑页面
        ActionContext.getContext().put("customer", cust);
        return "edit";
    }

    public String toDelete() {
        // 1.调用Service删除客户对象
        try {
            cs.delete(customer.getCust_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "toList";
    }

    public String industryCount() throws Exception {

        try {
            List<Object[]> list = cs.getIndustryCount();
            ActionContext.getContext().put("list", list);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "industryCount";
    }

    @Override
    public Customer getModel() {
        return customer;
    }

    public void setCs(CustomerService cs) {
        this.cs = cs;
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

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
