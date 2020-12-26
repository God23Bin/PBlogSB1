package com.bin23.blog.dao;

import com.bin23.blog.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BlogSortMapper {

    /**
     * 按类别id查询，即可查询出相同类别的博客
     * @param sortId
     * @return
     */
    @Select("SELECT id, title, author, content, comment_count, reading_count, like_count, create_time, update_time, blog_url, cover_url FROM adm_blog AS b LEFT JOIN adm_blog_sort AS bs ON b.id = bs.blog_id WHERE bs.sort_id = #{id}")
    List<Blog> selectBlogListBySortId(Integer sortId);

    /**
     * 在博客分类表中添加数据，某博客是某分类
     * @param blogId
     * @param sortId
     * @return
     */
    @Insert("INSERT INTO adm_blog_sort VALUES(#{blogId}, #{sortId})")
    int insertBlogSort(Integer blogId, Integer sortId);

    /**
     * 给博客修改分类，已存在的博客保存的时候需要用到
     * @param blogId
     * @param sortId
     * @return
     */
    @Update("UPDATE adm_blog_sort SET sort_id = #{sortId} WHERE blog_id = #{blogId}")
    int updateBlogSort(Integer blogId, Integer sortId);
}
