package com.bin23.blog.dao;

import com.bin23.blog.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * 获取用户id，通过手机号
     * @param phoneNumber
     * @return
     */
    @Select("SELECT id FROM sys_user WHERE phone_number = #{phoneNumber}")
    int selectIdByPhoneNumber(String phoneNumber);

    /**
     * 查询所有普通用户
     * @return
     */
    @Select("SELECT id, phone_number, username, password, email, avatar, signature, age, birthday, code_age, register_time, is_ban FROM sys_user AS su LEFT JOIN sys_user_role AS sur ON su.id = sur.user_id WHERE sur.role_id = 2")
    List<SysUser> selectAllSysUser();

    /**
     * 查询所有封禁/没封禁的普通用户
     * @return
     */
    @Select("SELECT id, phone_number, username, password, email, avatar, signature, age, birthday, code_age, register_time, is_ban FROM sys_user AS su LEFT JOIN sys_user_role AS sur ON su.id = sur.user_id WHERE sur.role_id = 2 AND su.is_ban = #{isBan}")
    List<SysUser> selectAllSysUserWithBanOrNot(Boolean isBan);

    /**
     * 封禁/解封用户
     * @param id
     * @param isBan
     * @return
     */
    @Update("UPDATE sys_user SET is_ban = #{isBan} WHERE id = #{id}")
    int updateUserIsBan(Integer id, Boolean isBan);

}
