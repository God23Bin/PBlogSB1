package com.bin23.blog.service.sort.impl;

import com.bin23.blog.dao.SortMapper;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.service.sort.SortService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {
    // 连续显示3页
    private static final Integer NAV_PAGES = 3;

    @Resource
    private SortMapper sortMapper;

    @Override
    public int addSort(Sort sort) {
        return sortMapper.insertSortIntoDB(sort);
    }

    @Override
    public List<Sort> getAllSort() {
        return sortMapper.selectAllSort();
    }

    @Override
    public PageInfo<Sort> getAllSortWithPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Sort> allSort = getAllSort();
        PageInfo<Sort> sortPageInfo = new PageInfo<>(allSort, NAV_PAGES);
        return sortPageInfo;
    }

    @Override
    public int updateSort(Sort sort) {
        return sortMapper.updateSort(sort);
    }

    @Override
    public int deleteSortById(Integer id) {
        return sortMapper.deleteSortById(id);
    }

    @Override
    public int getAllSortCount() {
        return getAllSort().size();
    }
}
