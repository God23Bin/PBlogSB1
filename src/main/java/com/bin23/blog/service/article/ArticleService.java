package com.bin23.blog.service.article;

import com.bin23.blog.entity.Blog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleService {

    /**
     * 添加博客
     * @param blog
     */
    int addBlog(Blog blog);

    /**
     * 获取所有博客
     * @return
     */
    List<Blog> getAllBlog();

    /**
     * 分页查询所有博客
     * @param pageNum 当前页码
     * @param pageSize  每页几条数据，即大小
     * @return
     */
    PageInfo<Blog> getAllBlogWithPage(Integer pageNum, Integer pageSize);

    /**
     * 根据id获取博客
     * @param id
     * @return
     */
    Blog getBlogById(Integer id);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * 根据id删除博客
     * @param id
     * @return
     */
    int deleteBlogById(Integer id);

    /**
     * 根据分类获取文章
     * @param sortName
     * @return
     */
    List<Blog> selectBlogListBySort(String sortName);


}
