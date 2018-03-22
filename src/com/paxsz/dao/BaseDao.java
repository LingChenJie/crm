package com.paxsz.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

    /**
     * 增
     *
     * @param t
     */
    void save(T t);

    /**
     * 删
     *
     * @param t
     */
    void delete(T t);

    /**
     * 删
     *
     * @param id
     */
    void delete(Serializable id);

    /**
     * 改
     *
     * @param t
     */
    void update(T t);

    /**
     * 查 根据id查询
     *
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 查 符合条件的总记录数
     *
     * @param dc
     * @return
     */
    Integer getTotalCount(DetachedCriteria dc);

    /**
     * 查 查询分页列表数据
     *
     * @param dc
     * @param start
     * @param pageSize
     * @return
     */
    List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize);

    /**
     * 曾或修改
     *
     * @param t
     */
    void saveOrUpdate(T t);
}
