package com.bin23.blog.dao;

import com.bin23.blog.entity.ImgEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgEntityMapper {

    @Select("SELECT * FROM adm_img")
    List<ImgEntity> selectAllImg();

    @Select("SELECT id FROM adm_img WHERE img_url = #{imgUrl}")
    int selectIdByImgUrl(String imgUrl);

    @Select("SELECT img_url FROM adm_img WHERE id = #{id}")
    int selectImgUrlById(Integer id);

    @Delete("DELETE FROM adm_img WHERE id = #{id}")
    int deleteImgById(Integer id);
}
