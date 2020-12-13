package com.bin23.blog.service.impl;

import com.bin23.blog.dao.SysUserRoleMapper;
import com.bin23.blog.entity.SysUserRole;
import com.bin23.blog.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}
