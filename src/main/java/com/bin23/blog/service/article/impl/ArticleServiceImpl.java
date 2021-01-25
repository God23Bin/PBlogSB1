package com.bin23.blog.service.article.impl;

import com.bin23.blog.dao.BlogMapper;
import com.bin23.blog.dao.BlogSortMapper;
import com.bin23.blog.dao.SortMapper;
import com.bin23.blog.entity.Blog;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.service.article.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    // 连续显示3页
    private static final Integer NAV_PAGES = 3;

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private SortMapper sortMapper;

    @Resource
    private BlogSortMapper blogSortMapper;

    @Override
    public int addBlog(Blog blog) {
        return blogMapper.insertBlogIntoDB(blog);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.selectAllBlog();
    }

    @Override
    public PageInfo<Blog> getAllBlogWithPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.selectAllBlog();
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs, NAV_PAGES);
//        System.out.println("查到的博客：" + blogPageInfo.getList());
//        blogPageInfo.getList().forEach(p-> System.out.println(p.toString()));
        return blogPageInfo;
    }

    /**
     * 分页查询所有博客，以是否发布来查询，替代上面的
     * @param isPublish
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Blog> getAllBlogByIsPublishWithPage(Boolean isPublish, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.selectAllBlogByIsPublish(isPublish);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs, NAV_PAGES);
        return blogPageInfo;
    }

    /**
     * 分页查询所有在垃圾箱中的博客
     * @param isTrash
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Blog> getAllBlogByIsTrashWithPage(Boolean isTrash, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.selectAllBlogByIsTrash(isTrash);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs, NAV_PAGES);
        return blogPageInfo;
    }

    /**
     * 可分页查询 我的文章-即已发布，不在垃圾箱的所有文章
     *            草稿箱- 即未发布，不在垃圾箱的所有文章
     *            垃圾箱- 即已发布/未发布，在垃圾箱的所有文章
     * @param isPublish 是否发布
     * @param isTrash   是否为垃圾
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Blog> getAllBlogByIsPublishAndIsTrashWithPage(Boolean isPublish, Boolean isTrash, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.selectAllBlogByIsPublishAndIsTrash(isPublish, isTrash);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs, NAV_PAGES);
        return blogPageInfo;
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogMapper.selectBlogById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlogById(Integer id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public List<Blog> selectBlogListBySort(String sortName) {
        // 根据分类名查询出该分类
        Sort sort = sortMapper.selectSortBySortName(sortName);
        // 得到该分类后，获取该分类id，进而查询有相同分类id的博客，说明查询出的是同一类别的博客
        return blogSortMapper.selectBlogListBySortId(sort.getId());
    }

    @Override
    public int getIdByBlogTitle(String title) {
        return blogMapper.selectIdByBlogTitle(title);
    }

    @Override
    public int getAllBlogCount() {
        return getAllBlog().size();
    }
}
