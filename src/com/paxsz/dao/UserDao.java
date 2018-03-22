package com.paxsz.dao;

import com.paxsz.bean.User;

public interface UserDao extends BaseDao<User> {

    /**
     * 根据登录名称查询user对象
     *
     * @param userCode
     * @return
     */
    User getByUserCode(String userCode);
}
