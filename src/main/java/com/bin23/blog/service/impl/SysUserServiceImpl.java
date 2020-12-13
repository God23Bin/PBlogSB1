package com.bin23.blog.service.impl;

import com.bin23.blog.dao.SysUserMapper;
import com.bin23.blog.entity.SysUser;
import com.bin23.blog.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper userMapper;

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

    @Override
    public int sysUserRegister(SysUser sysUser) {
        return userMapper.insertIntoDB(sysUser);
    }
}
