package com.paxsz.utils;

import java.util.List;

public class PageBean {

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 总记录条数
     */
    private Integer totalCount;

    /**
     * 每页显示的条数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前的记录条数
     */
    private List list;

    /**
     * @param currentPage 当前页码
     * @param totalCount  总条数
     * @param pageSize    每页显示条数
     */
    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;

        if (this.currentPage == null) {
            // 如页面没有指定显示那一页.显示第一页
            this.currentPage = 1;
        }

        if (this.pageSize == null) {
            // 如果每页显示条数没有指定,默认每页显示3条
            this.pageSize = 3;
        }

        //计算总页数
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;

        // 判断当前页码是否超出范围
        if (this.currentPage < 1) {
            this.currentPage = 1;
        }
        if (this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }

    }

    /**
     * 计算起始索引
     *
     * @return
     */
    public int getStart() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
