package com.bin23.blog.service.sort.impl;

import com.bin23.blog.dao.BlogSortMapper;
import com.bin23.blog.entity.Blog;
import com.bin23.blog.service.sort.BlogSortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogSortServiceImpl implements BlogSortService {

    @Resource
    private BlogSortMapper blogSortMapper;

    @Override
    public List<Blog> getBlogListBySortId(Integer sortId) {
        return blogSortMapper.selectBlogListBySortId(sortId);
    }

    @Override
    public int addBlogSort(Integer blogId, Integer sortId) {
        return blogSortMapper.insertBlogSort(blogId, sortId);
    }

    @Override
    public int updateBlogSort(Integer blogId, Integer sortId) {
        return blogSortMapper.updateBlogSort(blogId, sortId);
    }
}
