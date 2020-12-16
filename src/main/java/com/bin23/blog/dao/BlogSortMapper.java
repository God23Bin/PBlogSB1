package com.bin23.blog.dao;

import com.bin23.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
