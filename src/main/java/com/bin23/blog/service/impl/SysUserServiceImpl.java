package com.bin23.blog.service.impl;

import com.bin23.blog.dao.SysUserMapper;
import com.bin23.blog.dao.SysUserRoleMapper;
import com.bin23.blog.entity.SysUser;
import com.bin23.blog.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Integer ROLE_ADMIN = 1;
    private static final Integer ROLE_USER = 2;

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUser selectByPhoneNumber(String phoneNumber) {
        return userMapper.selectByPhoneNumber(phoneNumber);
    }

    @Override
    public SysUser selectByName(String username) {
        return userMapper.selectByName(username);
    }

    /**
     * 用户注册，同时赋予普通用户的角色
     * @param sysUser
     * @return
     */
    @Override
    public int sysUserRegister(SysUser sysUser) {
        int i = userMapper.insertIntoDB(sysUser);
        int idByPhoneNumber = userMapper.getIdByPhoneNumber(sysUser.getPhoneNumber());
        SysUser user = userMapper.selectById(idByPhoneNumber);
        // 添加角色
        userRoleMapper.insertUserRole(user.getId(), ROLE_USER);
        return i;
    }
}
