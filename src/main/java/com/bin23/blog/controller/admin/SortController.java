package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.Sort;
import com.bin23.blog.service.sort.SortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SortController {

    @Resource
    private SortService sortService;

    @GetMapping("/getAllSort")
    @ResponseBody
    public List<Sort> getAllSort() {
        List<Sort> allSort = sortService.getAllSort();
        return allSort;
    }
}
