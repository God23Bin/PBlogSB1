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
     * 分页查询所有博客，以是否发布来查询，替代上面的
     * @param isPublish
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Blog> getAllBlogByIsPublishWithPage(Boolean isPublish, Integer pageNum, Integer pageSize);

    /**
     * 分页查询所有在垃圾箱中的博客
     * @param isTrash
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Blog> getAllBlogByIsTrashWithPage(Boolean isTrash, Integer pageNum, Integer pageSize);

    /**
     * 分页查询所有博客，是否发布，是否为垃圾
     * @param isPublish
     * @param isTrash
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Blog> getAllBlogByIsPublishAndIsTrashWithPage(Boolean isPublish, Boolean isTrash, Integer pageNum, Integer pageSize);

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

    /**
     * 根据博客标题获取博客id
     * @param title
     * @return
     */
    int getIdByBlogTitle(String title);

    /**
     * 获取博客的总数量
     * @return
     */
    int getAllBlogCount();

}
