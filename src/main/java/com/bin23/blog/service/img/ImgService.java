package com.bin23.blog.service.img;

import com.bin23.blog.entity.ImgEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ImgService {
    List<ImgEntity> getAllImg();
    PageInfo<ImgEntity> getAllImgWithPage(Integer pageNum, Integer pageSize);
    int getIdByImgUrl(String imgUrl);
    int getImgUrlById(Integer id);
    int deleteImgById(Integer id);
}
