package com.bin23.blog.service.sort;

import com.bin23.blog.entity.Sort;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SortService {
    /**
     * 添加类别
     * @param sort
     * @return
     */
    int addSort(Sort sort);

    /**
     * 获取全部分类
     * @return
     */
    List<Sort> getAllSort();

    /**
     * 分页获取全部分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Sort> getAllSortWithPage(Integer pageNum, Integer pageSize);

    /**
     * 更新类别
     * @param sort
     * @return
     */
    int updateSort(Sort sort);

    /**
     * 根据id删除类别
     * @param id
     * @return
     */
    int deleteSortById(Integer id);

    /**
     * 获取分类的总数量
     * @return
     */
    int getAllSortCount();
}
