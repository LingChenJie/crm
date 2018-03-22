package com.paxsz.dao.impl;

import com.paxsz.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    /**
     *  getClass()：获取的是实际运行的类的字节码
     *  getGenericInterfaces()：以Type数组的形式返回本类直接实现的接口列表，包含了泛型参数信息
     *  getGenericSuperclass()：以Type数组的形式返回本类直接继承的类列表，包含了泛型参数信息
     *  getActualTypeArguments() ：获取泛型类型的实际类型参数集
     */

    // 用于接收运行期泛型类型
    private Class clazz;

    public BaseDaoImpl() {
        // 获得当前类的带有泛型类型的父类
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获得运行期的泛型类型
        clazz = (Class) ptClass.getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public void delete(Serializable id) {
        T t = getById(id);
        delete(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public T getById(Serializable id) {
        return (T) getHibernateTemplate().get(clazz, id);
    }

    @Override
    public Integer getTotalCount(DetachedCriteria dc) {
        // 设置查询的聚合函数,总记录数
        dc.setProjection(Projections.rowCount());

        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);

        //清空之前设置的聚合函数
        dc.setProjection(null);

        return (list != null && list.size() > 0) ? list.get(0).intValue() : 0;
    }

    @Override
    public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
        return list;
    }

    @Override
    public void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }
}
