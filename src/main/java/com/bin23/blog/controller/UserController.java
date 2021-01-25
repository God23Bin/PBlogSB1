package com.bin23.blog.controller;

import com.bin23.blog.entity.Blog;
import com.bin23.blog.entity.Label;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.entity.SysUser;
import com.bin23.blog.service.article.ArticleService;
import com.bin23.blog.service.impl.SysUserServiceImpl;
import com.bin23.blog.service.label.LabelService;
import com.bin23.blog.service.sort.SortService;
import com.bin23.blog.utils.MarkDownUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String PAGE_NUM = "1";
    private static final String PAGE_SIZE = "5";

    @Resource
    private SysUserServiceImpl sysUserService;

    @Resource
    private ArticleService articleService;

    @Resource
    private SortService sortService;

    @Resource
    private LabelService labelService;

    @RequestMapping("/")
    public String showIndex(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                            @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                            Model model) {
        // 获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        // 获取博文和分页信息
        PageInfo<Blog> allBlogWithPage = articleService.getAllBlogWithPage(pageNum, pageSize);
        List<Blog> allBlog = allBlogWithPage.getList();
        // 获取分类
        List<Sort> allSort = sortService.getAllSort();
        // 获取标签
        List<Label> allLabel = labelService.getAllLabel();
        model.addAttribute("allBlog", allBlog);
        model.addAttribute("pageInfo", allBlogWithPage);
        model.addAttribute("allSort", allSort);
        model.addAttribute("allLabel", allLabel);
        return "index";
    }

    @GetMapping("/userLogin")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "你有ROLE_USER角色";
    }

    @GetMapping("/toRegisterPage")
    public String toRegisterPage(){
        return "register";
    }

    @PostMapping("/userRegister")
    public String userRegister(SysUser sysUser){
        String password = sysUser.getPassword();
        BCryptPasswordEncoder bcryptPasswordEncoder  = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(password);
        sysUser.setPassword(hashPass);
        int i = sysUserService.sysUserRegister(sysUser);
        logger.info("注册返回值为1即成功注册：" + i);
        return "index";
    }

    @GetMapping("/article/details/{blogId}")
    public String blogDetails(@PathVariable("blogId") Integer blogId, Model model) {
        Blog blog = articleService.getBlogById(blogId);
        String blogHtmlContent = MarkDownUtil.mdToHtml(blog.getContent());
//        model.addAttribute("blogBgUrl", "url(http://localhost:8080" + blog.getCoverUrl() +")");
        // 更新封面获取
        model.addAttribute("blogBgUrl", "url(" + blog.getCoverUrl() +")");
        model.addAttribute("blogTitle", blog.getTitle());
        model.addAttribute("blogContent", blogHtmlContent);
        return "articles";
    }

    @RequestMapping("/zone/game")
    public String gamezone(){
        return "gamezone";
    }

    @RequestMapping("/zone/music")
    public String musiczone(){
        return "musiczone";
    }

    @RequestMapping("/zone/movie")
    public String moviezone(){
        return "moviezone";
    }

    @RequestMapping("/about")
    public String aboutMe(){
        return "about_me";
    }
}
