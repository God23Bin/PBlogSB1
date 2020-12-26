package com.bin23.blog.dao;

import com.bin23.blog.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     * 查询博客
     */
    @Select("SELECT * FROM adm_blog")
    List<Blog> selectAllBlog();

    @Select("SELECT * FROM adm_blog WHERE id = #{id}")
    Blog selectBlogById(Integer id);

    /**
     * 按是否发布来查询博客，可区分是在我的文章中还是在草稿箱，替代之前的直接查询全部的
     * @param isPublish
     * @return
     */
    @Select("SELECT * FROM adm_blog WHERE is_publish = #{isPublish}")
    List<Blog> selectAllBlogByIsPublish(Boolean isPublish);

    /**
     * 查询已经在垃圾箱中的所有博客
     * @param isTrash
     * @return
     */
    @Select("SELECT * FROM adm_blog WHERE is_trash = #{isTrash}")
    List<Blog> selectAllBlogByIsTrash(Boolean isTrash);

    /**
     * -- 对上面的  查询已经发布的博客进行SQL语句的完善
     * @param isPublish
     * @param isTrash
     * @return
     */
    @Select("SELECT * FROM adm_blog WHERE is_publish = #{isPublish} AND is_trash = #{isTrash}")
    List<Blog> selectAllBlogByIsPublishAndIsTrash(Boolean isPublish, Boolean isTrash);


    /**
     * 添加博客
     * 1. 新增状态，是否发布和是否为垃圾
     */
    @Insert("INSERT INTO adm_blog (title, author, content, create_time, update_time, blog_url, cover_url, is_publish, is_trash) VALUES (#{title}, #{author}, #{content}, #{createTime}, #{updateTime}, #{blogUrl}, #{coverUrl}, #{isPublish}, #{isTrash})")
    int insertBlogIntoDB(Blog blog);

    /**
     * 修改博客
     * 1. 新增状态，是否发布和是否为垃圾
     */
    @Update("UPDATE adm_blog SET title = #{title}, content = #{content}, update_time = #{updateTime}, cover_url = #{coverUrl}, is_publish = #{isPublish}, is_trash = #{isTrash} WHERE id = #{id}")
    int updateBlog(Blog blog);

    /**
     * 删除博客
     */
    @Delete("DELETE FROM adm_blog WHERE id = #{id}")
    int deleteBlogById(Integer id);

    /**
     * 根据博客标题获取博客id
     */
    @Select("SELECT id FROM adm_blog WHERE title = #{title}")
    int selectIdByBlogTitle(String title);

}
