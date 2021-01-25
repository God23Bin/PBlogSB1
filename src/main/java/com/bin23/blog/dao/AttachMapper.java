package com.bin23.blog.dao;

import com.bin23.blog.entity.Attach;
import com.bin23.blog.entity.ImgEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttachMapper {

    @Select("SELECT * FROM adm_attach")
    List<Attach> selectAllAttach();

    @Select("SELECT id FROM adm_attach WHERE attach_url = #{attachUrl}")
    int selectIdByAttachUrl(String attachUrl);

    @Select("SELECT attach_url FROM adm_attach WHERE id = #{id}")
    int selectAttachUrlById(Integer id);

    @Delete("DELETE FROM adm_attach WHERE id = #{id}")
    int deleteAttachById(Integer id);
}
