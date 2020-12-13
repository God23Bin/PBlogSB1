package com.bin23.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
}
