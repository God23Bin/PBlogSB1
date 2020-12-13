package com.bin23.blog.service.impl;

import com.bin23.blog.dao.SysRoleMapper;
import com.bin23.blog.entity.SysRole;
import com.bin23.blog.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public SysRole selectById(Integer id) {
        return roleMapper.selectById(id);
    }
}
