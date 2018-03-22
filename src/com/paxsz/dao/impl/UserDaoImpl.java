package com.paxsz.dao.impl;

import com.paxsz.bean.User;
import com.paxsz.dao.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getByUserCode(String userCode) {

        //Criteria
//        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
//        dc.add(Restrictions.eq("user_code", userCode));
//        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
//        return (list != null && list.size() > 0) ? list.get(0) : null;

        //HQL
        User user = getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String sql = "from User where user_code = ?";
                Query query = session.createQuery(sql);
                query.setParameter(0, userCode);
                User user = (User) query.uniqueResult();
                return user;
            }
        });
        return user;
    }
}
