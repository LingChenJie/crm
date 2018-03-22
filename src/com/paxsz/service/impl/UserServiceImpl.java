package com.paxsz.service.impl;

import com.paxsz.bean.User;
import com.paxsz.dao.UserDao;
import com.paxsz.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {

    private UserDao ud;

    @Override
    public User getUserByCodePassword(User u) {
        User queryU = ud.getByUserCode(u.getUser_code());
        if (queryU == null) {
            throw new RuntimeException("用户名不存在");
        }
        if (!queryU.getUser_password().equals(u.getUser_password())) {
            throw new RuntimeException("密码错误");
        }
        return queryU;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveUser(User u) {
        ud.save(u);
    }

    public void setUd(UserDao ud) {
        this.ud = ud;
    }
}
