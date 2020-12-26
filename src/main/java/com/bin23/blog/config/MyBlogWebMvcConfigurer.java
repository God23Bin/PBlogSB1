package com.bin23.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyBlogWebMvcConfigurer implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径("file:"是规定写法)
        // 通俗来说，当访问/upload/下的路径时，都会去D:/MyZone/Blog_AboutUpload/找资源
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + "D:/MyZone/Blog_AboutUpload/");
    }
}
