package com.bin23.blog.service.sort.impl;

import com.bin23.blog.dao.SortMapper;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.service.sort.SortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {

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
    public int updateSort(Sort sort) {
        return sortMapper.updateSort(sort);
    }

    @Override
    public int deleteSortById(Integer id) {
        return sortMapper.deleteSortById(id);
    }
}
