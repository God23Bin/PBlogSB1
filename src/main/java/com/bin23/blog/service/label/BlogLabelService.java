package com.bin23.blog.service.label;

import com.bin23.blog.entity.Blog;

import java.util.List;

public interface BlogLabelService {
    /**
     * 按标签的id查询相同标签的博客列表
     * @param labelId
     * @return
     */
    List<Blog> getSameLabelBlogListByLabelId(Integer labelId);

    /**
     * 在博客标签表中添加数据，某博客是某标签
     * @param blogId
     * @param labelId
     * @return
     */
    int addBlogLabel(Integer blogId, Integer labelId);

    /**
     * 删除某篇博客的某个标签
     * @param blogId
     * @param labelId
     * @return
     */
    int deleteBlogLabel(Integer blogId, Integer labelId);
}
