package com.bin23.blog.service.label.impl;

import com.bin23.blog.dao.BlogLabelMapper;
import com.bin23.blog.entity.Blog;
import com.bin23.blog.service.label.BlogLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogLabelServiceImpl implements BlogLabelService {

    @Resource
    private BlogLabelMapper blogLabelMapper;

    @Override
    public List<Blog> getSameLabelBlogListByLabelId(Integer labelId) {
        return blogLabelMapper.selectBlogListByLabelId(labelId);
    }

    @Override
    public int addBlogLabel(Integer blogId, Integer labelId) {
        return blogLabelMapper.insertBlogLabel(blogId, labelId);
    }

    @Override
    public int deleteBlogLabel(Integer blogId, Integer labelId) {
        return deleteBlogLabel(blogId, labelId);
    }
}
