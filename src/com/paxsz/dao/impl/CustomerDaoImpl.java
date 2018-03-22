package com.paxsz.dao.impl;

import com.paxsz.bean.Customer;
import com.paxsz.dao.CustomerDao;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    @Override
    public List<Object[]> getIndustryCount() {
        List list = getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                String sql = "SELECT" +
                        " bd.'dict_item_name'," +
                        " COUNT(*) total" +
                        " FROM" +
                        " cust_customer c," +
                        " base_dict bd" +
                        " WHERE c.'cust_industry' = bd.'dict_id'" +
                        " GROUP BY c.'cust_industry'";
                SQLQuery query = session.createSQLQuery(sql);
                return query.list();
            }
        });
        return list;
    }
}
