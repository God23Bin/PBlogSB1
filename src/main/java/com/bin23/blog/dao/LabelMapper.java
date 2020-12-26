package com.bin23.blog.dao;

import com.bin23.blog.entity.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabelMapper {

    /**
     * 查询标签
     */
    @Select("SELECT * FROM adm_label")
    List<Label> selectAllLabel();

    @Select("SELECT * FROM adm_label WHERE id = #{id}")
    Label selectLabelById(Integer id);

    @Select("SELECT * FROM adm_label WHERE label_name = #{labelName}")
    Label selectLabelByLabelName(String labelName);

    /**
     * 添加标签
     */
    @Insert("INSERT INTO adm_label (label_name) VALUES (#{labelName})")
    int insertLabelIntoDB(Label label);

    /**
     * 修改标签
     */
    @Update("UPDATE adm_label SET label_name = #{labelName} WHERE id = #{id}")
    int updateLabel(Label label);

    /**
     * 删除标签
     */
    @Delete("DELETE FROM adm_label WHERE id = #{id}")
    int deleteLabelById(Integer id);
}
