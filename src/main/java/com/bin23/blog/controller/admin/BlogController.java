package com.bin23.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @PostMapping("/save_blog")
    public String saveBlog() {
        return "";
    }
}
