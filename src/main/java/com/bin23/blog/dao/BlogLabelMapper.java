package com.bin23.blog.dao;

import com.bin23.blog.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogLabelMapper {

    /**
     * 按标签的id查询相同标签的博客列表
     * @param labelId
     * @return
     */
    @Select("SELECT id, title, author, content, comment_count, reading_count, like_count, create_time, update_time, blog_url, cover_url FROM adm_blog AS b LEFT JOIN adm_blog_label AS bl ON b.id = bl.blog_id WHERE bl.label_id = #{labelId}")
    List<Blog> selectBlogListByLabelId(Integer labelId);

    /**
     * 在博客标签表中添加数据，某博客是某标签
     * @param blogId
     * @param labelId
     * @return
     */
    @Insert("INSERT INTO adm_blog_label VALUES(#{blogId}, #{labelId})")
    int insertBlogLabel(Integer blogId, Integer labelId);

    /**
     * 给博客修改分类，已存在的博客保存的时候需要用到
     * 弃用
     * @param blogId
     * @param labelId
     * @return
     */
//    @Update("UPDATE adm_blog_label SET label_id = #{labelId} WHERE blog_id = #{blogId}")
//    int updateBlogSort(Integer blogId, Integer labelId);

    /**
     * 删除某篇博客的某个标签
     * @param blogId
     * @param labelId
     * @return
     */
    @Delete("DELETE FROM adm_blog_label WHERE blog_id = #{blogId} AND label_id = #{labelId}")
    int deleteBlogLabel(Integer blogId, Integer labelId);
}
