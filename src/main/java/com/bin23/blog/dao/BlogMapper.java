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
     * 添加博客
     */
    @Insert("INSERT INTO adm_blog (title, author, content, create_time, update_time, blog_url, cover_url) VALUES (#{title}, #{author}, #{content}, #{createTime}, #{updateTime}, #{blogUrl}, #{coverUrl})")
    int insertBlogIntoDB(Blog blog);

    /**
     * 修改博客
     */
    @Update("UPDATE adm_blog SET title = #{title}, content = #{content}, update_time = #{updateTime}, cover_url = #{coverUrl} WHERE id = #{id}")
    int updateBlog(Blog blog);

    /**
     * 删除博客
     */
    @Delete("DELETE FROM adm_blog WHERE id = #{id}")
    int deleteBlogById(Integer id);
}
