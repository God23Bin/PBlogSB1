package com.bin23.blog.service.attach.impl;

import com.bin23.blog.dao.AttachMapper;
import com.bin23.blog.entity.Attach;
import com.bin23.blog.service.attach.AttachService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachServiceImpl implements AttachService {
    // 连续显示3页
    private static final Integer NAV_PAGES = 3;

    @Resource
    private AttachMapper attachMapper;

    @Override
    public List<Attach> getAllAttach() {
        return attachMapper.selectAllAttach();
    }

    @Override
    public PageInfo<Attach> getAllAttachWithPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Attach> allAttach = getAllAttach();
        PageInfo<Attach> attachPageInfo = new PageInfo<>(allAttach, NAV_PAGES);
        return attachPageInfo;
    }

    @Override
    public int getIdByAttachUrl(String attachUrl) {
        return attachMapper.selectIdByAttachUrl(attachUrl);
    }

    @Override
    public int getAttachUrlById(Integer id) {
        return attachMapper.selectAttachUrlById(id);
    }

    @Override
    public int deleteAttachById(Integer id) {
        return attachMapper.deleteAttachById(id);
    }
}
