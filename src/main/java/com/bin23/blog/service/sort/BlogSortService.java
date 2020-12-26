package com.bin23.blog.service.sort;

import com.bin23.blog.entity.Blog;

import java.util.List;

public interface BlogSortService {
    /**
     * 按类别id查询，即可查询出相同类别的博客
     * @param sortId
     * @return
     */
    List<Blog> getBlogListBySortId(Integer sortId);

    /**
     * 在博客分类表中添加数据，某博客是某分类
     * @param blogId
     * @param sortId
     * @return
     */
    int addBlogSort(Integer blogId, Integer sortId);

    /**
     * 给博客修改分类
     * @param blogId
     * @param sortId
     * @return
     */
    int updateBlogSort(Integer blogId, Integer sortId);
}
