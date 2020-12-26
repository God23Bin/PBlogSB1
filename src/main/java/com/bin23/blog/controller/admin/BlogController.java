package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.Blog;
import com.bin23.blog.entity.Label;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.entity.result.CResultGenerator;
import com.bin23.blog.entity.result.CommonResult;
import com.bin23.blog.service.article.ArticleService;
import com.bin23.blog.service.label.BlogLabelService;
import com.bin23.blog.service.label.LabelService;
import com.bin23.blog.service.sort.BlogSortService;
import com.bin23.blog.service.sort.SortService;
import com.bin23.blog.utils.MyBlogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String FILE_UPLOAD_DIC = "D:\\MyZone\\Blog_AboutUpload\\";
    private static final String AUTHOR = "god23bin";
    // 1. 增加文章状态
    private static final Boolean IS_PUBLISH = true;
    private static final Boolean NOT_PUBLISH = false;
    private static final Boolean IS_TRASH = true;
    private static final Boolean NOT_TRASH = false;

    @Resource
    private ArticleService articleService;

    @Resource
    private BlogSortService blogSortService;

    @Resource
    private BlogLabelService blogLabelService;

    @Resource
    private SortService sortService;

    @Resource
    private LabelService labelService;

    @PostMapping("/blog/md/upload_img")
    @ResponseBody
    public void uploadFileByEditormd(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam(name = "editormd-image-file", required = true) MultipartFile file) throws IOException, URISyntaxException {
        // Editormd前端规定了后台必须返回给前端一个map且形式为{success:"1",message:"上传成功",url:"url"}，这里需要注意一下。
        Map<String,Object> resultMap = new HashMap<String,Object>();
        // 获取整体文件名，原名称
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        //我自定义的文件保存路径 D:\MyZone\Blog_AboutUpload\
        File destFile = new File("D:/MyZone/Blog_AboutUpload/" + newFileName);
        //这一步应该是为了数据的回显
        //将存储的图片地址返回给前端进行图片的回显拿到相应的图片
        //MyBlogUtils.getHost(new URI(request.getRequestURL() + ""))获取主机名
        String fileUrl = MyBlogUtil.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;
        File fileDirectory = new File("D:/MyZone/Blog_AboutUpload/");
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
        } catch (IOException e) {
            response.getWriter().write("{\"success\":0}");
        }
    }


//    @PostMapping({"/blog/md/upload_img_with_paste"})
//    @ResponseBody
//    public CommonResult upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
//        String fileName = file.getOriginalFilename();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        //生成文件名称通用方法
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        Random r = new Random();
//        StringBuilder tempName = new StringBuilder();
//        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
//        String newFileName = tempName.toString();
//        File fileDirectory = new File(FILE_UPLOAD_DIC);
//        //创建文件
//        File destFile = new File(FILE_UPLOAD_DIC + newFileName);
//        try {
//            if (!fileDirectory.exists()) {
//                if (!fileDirectory.mkdir()) {
//                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
//                }
//            }
//            file.transferTo(destFile);
//            CommonResult resultSuccess = CResultGenerator.genSuccessResult();
//            resultSuccess.setData(MyBlogUtil.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
//            return resultSuccess;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return CResultGenerator.genFailResult("文件上传失败");
//        }
//    }

    /**
     * 文章发布
     * 两种情况
     * 1. 直接写文章，此时没有id
     * 2. 草稿箱继续编辑进来的，此时有id
     * @param blogId
     * @param blogTitle
     * @param blogCategoryId
     * @param blogContent
     * @return
     */
    @PostMapping("/blog/add")
    @ResponseBody
    public CommonResult addBlog(@RequestParam("blogId") Integer blogId,
                                @RequestParam("blogTitle") String blogTitle,
                                @RequestParam("blogCategoryId") Integer blogCategoryId,
                                @RequestParam("blogTagIds[]") List<Integer> blogTagIds,
                                @RequestParam("blogContent") String blogContent) {
        // 定义时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日_HHmmss");
        // 搞定博客发布时间
        Date createTime = new Date();
        // 博客时间格式
        String format = sdf.format(createTime);
        if (blogId == null) {
            // 把前端传递过来的博客相关信息封装进Blog
            Blog blog = new Blog();
            blog.setTitle(blogTitle);
            blog.setAuthor(AUTHOR);
            blog.setContent(blogContent);
            blog.setCreateTime(createTime);
            articleService.addBlog(blog);
            // 给博客添加分类
            int blogIdGetByTitle = articleService.getIdByBlogTitle(blogTitle);
            blogSortService.addBlogSort(blogIdGetByTitle, blogCategoryId);
            // 给博客添加标签
            // 给博客添加标签
            for (int i = 0; i < blogTagIds.size(); i++) {
                blogLabelService.addBlogLabel(blogIdGetByTitle, blogTagIds.get(i));
            }
            // 给博客添加链接
            Blog blogById = articleService.getBlogById(blogId);
            blogById.setBlogUrl("/article/details/" + blogId);
            articleService.updateBlog(blogById);
        } else {
            Blog blog = articleService.getBlogById(blogId);
            blog.setTitle(blogTitle);
            blog.setAuthor(AUTHOR);
            blog.setContent(blogContent);
            blog.setCreateTime(createTime);
            // 设置成 IS_PUBLISH
            blog.setIsPublish(IS_PUBLISH);
            // 给博客添加链接
            blog.setBlogUrl("/article/details/" + blogId);
            // 主要更新下已存在的博客信息，切换状态成IS_PUBLISH
            articleService.updateBlog(blog);
            // 给博客添加分类
            blogSortService.addBlogSort(blogId, blogCategoryId);
            // 给博客添加标签
            // 给博客添加标签
            for (int i = 0; i < blogTagIds.size(); i++) {
                blogLabelService.addBlogLabel(blogId, blogTagIds.get(i));
            }
        }
        CommonResult resultSuccess = CResultGenerator.genSuccessResult();
        return resultSuccess;
    }

    /**
     * 博客的保存处理
     * 主要有两种情况
     * 1. 写一篇新的博客的时候点击保存
     * 2. 对已存在的博客进行编辑后，点击保存
     * @param blogId            id可能存在或者不存在，存在说明是第二种情况，不存在说明是第一种情况
     * @param blogTitle         存在Or不存在
     * @param blogCategoryId    存在Or不存在
     * @param blogContent       存在Or不存在
     * @return
     */
    @PostMapping("/blog/save")
    @ResponseBody
    public CommonResult saveBlog(@RequestParam("blogId") Integer blogId,
                                 @RequestParam("blogTitle") String blogTitle,
                                 @RequestParam("blogCategoryId") Integer blogCategoryId,
                                 @RequestParam("blogTagIds[]") List<Integer> blogTagIds,
                                 @RequestParam("blogContent") String blogContent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日_HHmmss");
        Date createTime = new Date();
        String format = sdf.format(createTime);

        // 如果blogId为null的话，说明是新博客，新文章，还未存入数据库，直接插入到博客表中，设置isPublish为false
        if (blogId == null) {
            Blog blog = new Blog();
            blog.setTitle(blogTitle);
            blog.setContent(blogContent);
            blog.setCreateTime(createTime);
            blog.setIsPublish(NOT_PUBLISH);
            articleService.addBlog(blog);
            // 给博客添加分类
            int blogIdGetByTitle = articleService.getIdByBlogTitle(blogTitle);
            blogSortService.addBlogSort(blogIdGetByTitle, blogCategoryId);
            // 给博客添加标签
            for (int i = 0; i < blogTagIds.size(); i++) {
                blogLabelService.addBlogLabel(blogIdGetByTitle, blogTagIds.get(i));
            }

        } else {
            // 不为null的话，说明是已存在的博客，是从我的文章或草稿箱页面点击修改过来编辑页面的，当保存的话就直接更新到博客表中，然后在我的文章或草稿箱中进行显示，此时不需要设置isPublish，因为已经存在的博文已经设置过了
            Blog blog = articleService.getBlogById(blogId);
            blog.setTitle(blogTitle);
            blog.setContent(blogContent);
            blog.setUpdateTime(createTime);
            articleService.updateBlog(blog);
            // 给博客修改分类
            blogSortService.updateBlogSort(blogId, blogCategoryId);
            // 给博客修改标签

        }
        CommonResult resultSuccess = CResultGenerator.genSuccessResult();
        return resultSuccess;
    }

    @GetMapping("/blog/edit")
    public String editBlog(@RequestParam(value = "blogId", required = false) Integer blogId, Model model) {
        Blog blog = new Blog();
        if (blogId != null) {
            blog = articleService.getBlogById(blogId);
        }
        model.addAttribute("blogWillBeEdited", blog);
        // 点击修改按钮跳转到编辑页面前，先存一波博客id
        // 方便后面进行判断，即保存的时候是进行更新数据还是直接插入表中
        model.addAttribute("blogId", blogId);
        List<Sort> allSort = sortService.getAllSort();
        model.addAttribute("allSort", allSort);
        List<Label> allLabel = labelService.getAllLabel();
        model.addAttribute("allLabel", allLabel);
        return "admin/edit";
    }

    /**
     * 扔进垃圾箱
     * @param blogId
     * @return
     */
    @GetMapping("/blog/drop")
    @ResponseBody
    public CommonResult dropBlog(@RequestParam("blogId") Integer blogId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日_HHmmss");
        Date createTime = new Date();
        String format = sdf.format(createTime);

        Blog blog = articleService.getBlogById(blogId);
        blog.setIsTrash(IS_TRASH);
        blog.setUpdateTime(createTime);
        articleService.updateBlog(blog);
        return CResultGenerator.genSuccessResult();
    }

    @GetMapping("blog/recover")
    @ResponseBody
    public CommonResult recoverBlog(@RequestParam("blogId") Integer blogId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日_HHmmss");
        Date createTime = new Date();
        String format = sdf.format(createTime);

        Blog blog = articleService.getBlogById(blogId);
        blog.setIsTrash(NOT_TRASH);
        blog.setUpdateTime(createTime);
        articleService.updateBlog(blog);
        return CResultGenerator.genSuccessResult();
    }

    @GetMapping("blog/delete")
    @ResponseBody
    public CommonResult deleteBlog(@RequestParam("blogId") Integer blogId) {
        articleService.deleteBlogById(blogId);
        return CResultGenerator.genSuccessResult();
    }

}
