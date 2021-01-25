package com.bin23.blog.service.img.impl;

import com.bin23.blog.dao.ImgEntityMapper;
import com.bin23.blog.entity.ImgEntity;
import com.bin23.blog.service.img.ImgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {
    // 连续显示3页
    private static final Integer NAV_PAGES = 3;

    @Resource
    private ImgEntityMapper imgMapper;

    @Override
    public List<ImgEntity> getAllImg() {
        return imgMapper.selectAllImg();
    }

    @Override
    public PageInfo<ImgEntity> getAllImgWithPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ImgEntity> allImg = getAllImg();
        PageInfo<ImgEntity> imgEntityPageInfo = new PageInfo<>(allImg, NAV_PAGES);
        return imgEntityPageInfo;
    }

    @Override
    public int getIdByImgUrl(String imgUrl) {
        return imgMapper.selectIdByImgUrl(imgUrl);
    }

    @Override
    public int getImgUrlById(Integer id) {
        return imgMapper.selectImgUrlById(id);
    }

    @Override
    public int deleteImgById(Integer id) {
        return imgMapper.deleteImgById(id);
    }
}
