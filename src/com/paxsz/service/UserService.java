package com.paxsz.service;

import com.paxsz.bean.User;

public interface UserService {

    /**
     * 登录方法
     *
     * @param u
     * @return
     */
    User getUserByCodePassword(User u);

    /**
     * 注册用户
     *
     * @param u
     */
    void saveUser(User u);
}
