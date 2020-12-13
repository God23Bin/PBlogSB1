package com.bin23.blog.dao;

import com.bin23.blog.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE phone_number = #{phoneNumber}")
    SysUser selectByPhoneNumber(String phoneNumber);

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    SysUser selectByName(String username);

    @Insert("INSERT INTO sys_user (phone_number, username, `password`, email) VALUES (#{phoneNumber}, #{username}, #{password}, #{email})")
    int insertIntoDB(SysUser sysUser);

}
