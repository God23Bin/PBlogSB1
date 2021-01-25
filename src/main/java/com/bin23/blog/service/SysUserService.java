package com.bin23.blog.service;

import com.bin23.blog.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysUserService {
    SysUser getUserById(Integer id);
    SysUser getUserByPhoneNumber(String phoneNumber);
    SysUser getUserByName(String username);
    int sysUserRegister(SysUser sysUser);

    int getIdByPhoneNumber(String phoneNumber);

    /**
     * 查询所有普通用户
     * @return
     */
    List<SysUser> getAllSysUser();

    /**
     * 查询所有封禁/没封禁的普通用户
     * @param isBan
     * @return
     */
    List<SysUser> getAllSysUserWithBanOrNot(Boolean isBan);

    /**
     * 分页查询所有用户
     * @param isBan
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SysUser> getAllUserWithPage(Boolean isBan, Integer pageNum, Integer pageSize);


    /**
     * 封禁/解封用户
     * @param id
     * @param isBan
     * @return
     */
    int updateUserIsBan(Integer id, Boolean isBan);
}
