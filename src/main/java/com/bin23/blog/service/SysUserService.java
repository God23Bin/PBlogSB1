package com.bin23.blog.service;

import com.bin23.blog.entity.SysUser;

public interface SysUserService {
    SysUser selectById(Integer id);
    SysUser selectByPhoneNumber(String phoneNumber);
    SysUser selectByName(String username);
    int sysUserRegister(SysUser sysUser);
}
