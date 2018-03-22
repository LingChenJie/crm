package com.paxsz.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.paxsz.bean.User;
import com.paxsz.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();
    private UserService us;

    public String login() throws Exception {
        // 调用Service执行登录逻辑
        User user = us.getUserByCodePassword(this.user);
        // 将返回的user对象放入session域
        ActionContext.getContext().getSession().put("user", user);
        // 重定向到首页
        return "toHome";
    }

    public String regist() throws Exception {
        // 1.调用Service保存注册用户
        try {
            us.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            ActionContext.getContext().put("error", e.getMessage());
            return "regist";
        }
        // 2.重定向到登录页面
        return "toLogin";
    }

    @Override
    public User getModel() {
        return user;
    }

    public void setUs(UserService us) {
        this.us = us;
    }
}
