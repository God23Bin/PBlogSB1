package com.bin23.blog.dao;

import com.bin23.blog.entity.Sort;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SortMapper {
    /**
     * 查询类别
     */
    @Select("SELECT * FROM adm_sort")
    List<Sort> selectAllSort();

    @Select("SELECT * FROM adm_sort WHERE id = #{id}")
    Sort selectSortById(Integer id);

    @Select("SELECT * FROM adm_sort WHERE sort_name = #{sortName}")
    Sort selectSortBySortName(String sortName);

    /**
     * 添加类别
     */
    @Insert("INSERT INTO adm_sort (sort_name) VALUES (#{sortName})")
    int insertSortIntoDB(Sort sort);

    /**
     * 修改类别
     */
    @Update("UPDATE adm_sort SET sort_name = #{sortName} WHERE id = #{id}")
    int updateSort(Sort sort);

    /**
     * 删除类别
     */
    @Delete("DELETE FROM adm_sort WHERE id = #{id}")
    int deleteSortById(Integer id);
}
