package com.bin23.blog.dao;

import com.bin23.blog.entity.SysUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);

    /**
     * 在用户角色表中添加数据，某用户是某角色
     * @param userId
     * @param roleId
     * @return
     */
    @Insert("INSERT INTO sys_user_role VALUES(#{userId}, #{roleId})")
    int insertUserRole(Integer userId, Integer roleId);
}
