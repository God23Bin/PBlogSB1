package com.bin23.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id;
    private String title;
    private String author;
    private String content;
    private Integer commentCount;
    private Integer readingCount;
    private Integer likeCount;
    private Date createTime;
    private Date updateTime;
    private String blogUrl;
    private String coverUrl;
    /**
     * 1. 新增状态属性，是否发布和是否为垃圾
     */
    private Boolean isPublish;
    private Boolean isTrash;
}
