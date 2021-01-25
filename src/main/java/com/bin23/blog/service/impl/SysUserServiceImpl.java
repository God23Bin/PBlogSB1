package com.bin23.blog.service.impl;

import com.bin23.blog.dao.SysUserMapper;
import com.bin23.blog.dao.SysUserRoleMapper;
import com.bin23.blog.entity.SysUser;
import com.bin23.blog.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    private static final Integer ROLE_ADMIN = 1;
    private static final Integer ROLE_USER = 2;
    // 连续显示3页
    private static final Integer NAV_PAGES = 3;

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    public SysUser getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUser getUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectByPhoneNumber(phoneNumber);
    }

    @Override
    public SysUser getUserByName(String username) {
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
        int idByPhoneNumber = userMapper.selectIdByPhoneNumber(sysUser.getPhoneNumber());
        SysUser user = userMapper.selectById(idByPhoneNumber);
        // 添加角色
        userRoleMapper.insertUserRole(user.getId(), ROLE_USER);
        return i;
    }

    @Override
    public int getIdByPhoneNumber(String phoneNumber) {
        return userMapper.selectIdByPhoneNumber(phoneNumber);
    }

    @Override
    public List<SysUser> getAllSysUser() {
        return userMapper.selectAllSysUser();
    }

    @Override
    public List<SysUser> getAllSysUserWithBanOrNot(Boolean isBan) {
        return userMapper.selectAllSysUserWithBanOrNot(isBan);
    }

    /**
     * 分页查询所有封禁/没封禁普通用户
     * @param isBan
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<SysUser> getAllUserWithPage(Boolean isBan, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> allSysUser = getAllSysUserWithBanOrNot(isBan);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(allSysUser, NAV_PAGES);
        return sysUserPageInfo;
    }

    @Override
    public int updateUserIsBan(Integer id, Boolean isBan) {
        return userMapper.updateUserIsBan(id, isBan);
    }
}
