package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.*;
import com.bin23.blog.entity.result.CResultGenerator;
import com.bin23.blog.entity.result.CommonResult;
import com.bin23.blog.service.SysUserService;
import com.bin23.blog.service.article.ArticleService;
import com.bin23.blog.service.img.ImgService;
import com.bin23.blog.service.label.LabelService;
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
    // 1. 增加文章状态
    private static final Boolean IS_PUBLISH = true;
    private static final Boolean NOT_PUBLISH = false;
    private static final Boolean IS_TRASH = true;
    private static final Boolean NOT_TRASH = false;
    // 2. 增加用户封禁状态
    private static final Boolean IS_BAN = true;
    private static final Boolean NOT_BAN = false;


    @Resource
    private ArticleService articleService;

    @Resource
    private SortService sortService;

    @Resource
    private LabelService labelService;

    @Resource
    private SysUserService userService;

    @Resource
    private ImgService imgService;

    /**
     * 进入后台管理页面
     * @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
     * @return
     */
    @RequestMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String toAdminPage(Model model) {
        // 文章总数
        int allBlogCount = articleService.getAllBlogCount();
        // 分类总数
        int allSortCount = sortService.getAllSortCount();
        // 标签总数
        int allLabelCount = labelService.getAllLabelCount();
        // 评论总数

        model.addAttribute("allBlogCount", allBlogCount);
        model.addAttribute("allSortCount", allSortCount);
        model.addAttribute("allLabelCount", allLabelCount);
        return "admin/index";
    }


    @RequestMapping("/my_articles")
    public String myBlogPage(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                             @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                             Model model) {
//        未分页查询，查询所有博客
//        List<Blog> allBlog = articleService.getAllBlog();
//        model.addAttribute("allBlog", allBlog);
        PageInfo<Blog> allBlogWithPage = articleService.getAllBlogByIsPublishAndIsTrashWithPage(IS_PUBLISH, NOT_TRASH, pageNum, pageSize);
        List<Blog> allBlog = allBlogWithPage.getList();
        model.addAttribute("allBlog", allBlog);
        model.addAttribute("pageInfo", allBlogWithPage);
        return "admin/my-articles";
    }

    // 此映射由BlogController中的进行替换
    @RequestMapping("/edit_blog")
    public String editBlogPage(Model model) {
        List<Sort> allSort = sortService.getAllSort();
        List<Label> allLabel = labelService.getAllLabel();
        model.addAttribute("allSort", allSort);
        model.addAttribute("allLabel", allLabel);
        return "admin/edit";
    }

    @RequestMapping("/drawcase")
    public String drawCasePage(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                               @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                               Model model) {
        PageInfo<Blog> allBlogWithPage = articleService.getAllBlogByIsPublishAndIsTrashWithPage(NOT_PUBLISH, NOT_TRASH,  pageNum, pageSize);
        List<Blog> allBlog = allBlogWithPage.getList();
        model.addAttribute("allBlog", allBlog);
        model.addAttribute("pageInfo", allBlogWithPage);
        return "admin/drawcase";
    }

    @RequestMapping("/bincase")
    public String binCasePage(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                              @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                              Model model) {
        PageInfo<Blog> allBlogWithPage = articleService.getAllBlogByIsTrashWithPage(IS_TRASH, pageNum, pageSize);
        List<Blog> allBlog = allBlogWithPage.getList();
        model.addAttribute("allBlog", allBlog);
        model.addAttribute("pageInfo", allBlogWithPage);
        return "admin/bincase";
    }

    @RequestMapping("/user_manage")
    public String userManagePage(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                                 @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                                 Model model) {
        PageInfo<SysUser> allUserWithPage = userService.getAllUserWithPage(NOT_BAN, pageNum, pageSize);
        List<SysUser> allSysUser = allUserWithPage.getList();
        model.addAttribute("allUser", allSysUser);
        model.addAttribute("pageInfo", allUserWithPage);
        return "admin/user-manage";
    }

    @RequestMapping("/class_label")
    public String classLabelPage(@RequestParam(value = "sortPageNum", required = false, defaultValue = PAGE_NUM)int sortPageNum,
                                 @RequestParam(value = "sortPageSize", required = false, defaultValue = "3")int sortPageSize,
                                 @RequestParam(value = "labelPageNum", required = false, defaultValue = PAGE_NUM)int labelPageNum,
                                 @RequestParam(value = "labelPageSize", required = false, defaultValue = "3")int labelPageSize,
                                 Model model) {
        // 情况1： 第一次进来，首页，显示3条，无参数，全部执行
        // 情况2:  分类点击下一页，sortPageNum > 1 ，标签还是首页
        //                                          标签不是首页
        PageInfo<Sort> allSortWithPage = sortService.getAllSortWithPage(sortPageNum, sortPageSize);
        List<Sort> allSort = allSortWithPage.getList();
        PageInfo<Label> allLabelWithPage = labelService.getAllLabelWithPage(labelPageNum, labelPageSize);
        List<Label> allLabel = allLabelWithPage.getList();
        model.addAttribute("allSort", allSort);
        model.addAttribute("allLabel", allLabel);
        model.addAttribute("sortPageInfo", allSortWithPage);
        model.addAttribute("labelPageInfo", allLabelWithPage);
        return "admin/class-label";
    }

    @RequestMapping("/comments")
    public String commentsPage() {
        return "admin/comments";
    }

    @RequestMapping("/img_manage")
    public String imgManagePage(@RequestParam(value = "page", required = false, defaultValue = PAGE_NUM)int pageNum,
                                @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE)int pageSize,
                                Model model) {
        PageInfo<ImgEntity> allImgWithPage = imgService.getAllImgWithPage(pageNum, pageSize);
        List<ImgEntity> allImg = allImgWithPage.getList();
        model.addAttribute("allImg", allImg);
        model.addAttribute("pageInfo", allImgWithPage);
        return "admin/img-manage";
    }

    @RequestMapping("/attach_manage")
    public String attachManagePage() {
        return "admin/attach-manage";
    }
}
