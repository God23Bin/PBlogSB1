package com.bin23.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer id;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;
    /**
     * 1. 新增属性
     */
    private String avatar;
    private String signature;
    private Integer age;
    private Date birthday;
    private Integer codeAge;
    private Date registerTime;
    // 是否封禁
    private Boolean isBan;
}
