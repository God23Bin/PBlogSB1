package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.Blog;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.service.article.ArticleService;
import com.bin23.blog.service.sort.SortService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String PAGE_NUM = "1";
    private static final String PAGE_SIZE = "5";

    @Resource
    private ArticleService articleService;

    @Resource
    private SortService sortService;

    /**
     * 进入后台管理页面
     * @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
     * @return
     */
    @RequestMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String toAdminPage() {
        return "admin/index";
    }


    @RequestMapping("/my_articles")
    public String myBlogPage(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                             @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                             Model model) {
//        未分页查询，查询所有博客
//        List<Blog> allBlog = articleService.getAllBlog();
//        model.addAttribute("allBlog", allBlog);
        PageInfo<Blog> allBlogWithPage = articleService.getAllBlogWithPage(pageNum, pageSize);
        List<Blog> allBlog = allBlogWithPage.getList();
        model.addAttribute("allBlog", allBlog);
        model.addAttribute("pageInfo", allBlogWithPage);
        return "admin/my-articles";
    }

    @RequestMapping("/edit_blog")
    public String editBlogPage(Model model) {
        List<Sort> allSort = sortService.getAllSort();
        model.addAttribute("allSort", allSort);
        return "admin/edit";
    }

    @RequestMapping("/drawcase")
    public String drawCasePage() {
        return "admin/drawcase";
    }

    @RequestMapping("/bincase")
    public String binCasePage() {
        return "admin/bincase";
    }
}
