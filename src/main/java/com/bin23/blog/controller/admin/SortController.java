package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.Sort;
import com.bin23.blog.entity.result.CResultGenerator;
import com.bin23.blog.entity.result.CommonResult;
import com.bin23.blog.service.sort.SortService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SortController {
    private static final String PAGE_NUM = "1";
    private static final String PAGE_SIZE = "5";

    @Resource
    private SortService sortService;

    @GetMapping("/getAllSort")
    @ResponseBody
    public List<Sort> getAllSort() {
        List<Sort> allSort = sortService.getAllSort();
        return allSort;
    }


    @PostMapping("/add/sort")
    @ResponseBody
    public CommonResult addSort(@RequestParam("sortName") String sortName) {
        Sort sort = new Sort();
        sort.setSortName(sortName);
        int i = sortService.addSort(sort);
        return CResultGenerator.genSuccessResult();
    }

    @RequestMapping("/getPageInfo/SortPageInfo")
    @ResponseBody
    public CommonResult sortPageInfo(@RequestParam(value = "sortPageNum", required = false, defaultValue = PAGE_NUM)int sortPageNum,
                                           @RequestParam(value = "sortPageSize", required = false, defaultValue = "3")int sortPageSize,
                                           Model model) {
        PageInfo<Sort> allSortWithPage = sortService.getAllSortWithPage(sortPageNum, sortPageSize);
        List<Sort> allSort = allSortWithPage.getList();
        model.addAttribute("allSort", allSort);
        model.addAttribute("sortPageInfo", allSortWithPage);
        CommonResult successResult = CResultGenerator.genSuccessResult();
        successResult.setData(model);
        return successResult;
    }

    @PostMapping("/update/sort")
    @ResponseBody
    public CommonResult updateSort(@RequestParam("sortId") Integer sortId, @RequestParam("sortName") String sortName){
        Sort sort = new Sort(sortId, sortName);
        sortService.updateSort(sort);
        return CResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete/sort")
    @ResponseBody
    public CommonResult deleteSort(@RequestParam("sortId") Integer sortId){
        sortService.deleteSortById(sortId);
        return CResultGenerator.genSuccessResult();
    }
}
