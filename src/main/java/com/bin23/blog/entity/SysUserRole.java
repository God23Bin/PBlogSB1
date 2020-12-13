package com.bin23.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole {
    static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer roleId;
}
