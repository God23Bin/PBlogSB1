package com.bin23.blog.service.attach;

import com.bin23.blog.entity.Attach;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AttachService {
    List<Attach> getAllAttach();
    PageInfo<Attach> getAllAttachWithPage(Integer pageNum, Integer pageSize);
    int getIdByAttachUrl(String attachUrl);
    int getAttachUrlById(Integer id);
    int deleteAttachById(Integer id);

}
