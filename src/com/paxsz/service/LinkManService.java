package com.paxsz.service;

import com.paxsz.bean.LinkMan;
import com.paxsz.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {

    /**
     * 保存联系人
     *
     * @param linkMan
     */
    void save(LinkMan linkMan);

    /**
     * 联系人列表
     *
     * @param dc
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    /**
     * 根据id获取LinkMan对象
     *
     * @param lkm_id
     * @return
     */
    LinkMan getById(Long lkm_id);

    /**
     * 根据id删除LinkMan对象
     *
     * @param lkm_id
     */
    void deleteById(Long lkm_id);
}
