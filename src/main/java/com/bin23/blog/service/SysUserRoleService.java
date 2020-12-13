package com.bin23.blog.service;

import com.bin23.blog.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService {
    List<SysUserRole> listByUserId(Integer userId);
}
