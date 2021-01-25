package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.Label;
import com.bin23.blog.entity.Sort;
import com.bin23.blog.entity.result.CResultGenerator;
import com.bin23.blog.entity.result.CommonResult;
import com.bin23.blog.service.label.LabelService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LabelController {
    private static final String PAGE_NUM = "1";
    private static final String PAGE_SIZE = "5";

    @Resource
    private LabelService labelService;

    @GetMapping("/getAllLabel")
    @ResponseBody
    public List<Label> getAllLabel() {
        List<Label> allLabel = labelService.getAllLabel();
        return allLabel;
    }

    @PostMapping("/add/label")
    @ResponseBody
    public CommonResult addLabel(@RequestParam("labelName") String labelName) {
        Label label = new Label();
        label.setLabelName(labelName);
        int i = labelService.addLabel(label);
        return CResultGenerator.genSuccessResult();
    }

    @RequestMapping("/getPageInfo/LabelPageInfo")
    @ResponseBody
    public CommonResult labelPageInfo(@RequestParam(value = "labelPageNum", required = false, defaultValue = PAGE_NUM)int labelPageNum,
                                           @RequestParam(value = "labelPageSize", required = false, defaultValue = "3")int labelPageSize,
                                           Model model) {
        PageInfo<Label> allLabelWithPage = labelService.getAllLabelWithPage(labelPageNum, labelPageSize);
        List<Label> allLabel = allLabelWithPage.getList();
        model.addAttribute("allLabel", allLabel);
        model.addAttribute("labelPageInfo", allLabelWithPage);
        CommonResult successResult = CResultGenerator.genSuccessResult();
        successResult.setData(model);
        return successResult;
    }

    @PostMapping("/update/label")
    @ResponseBody
    public CommonResult updateLabel(@RequestParam("labelId") Integer labelId, @RequestParam("labelName") String labelName){
        Label label = new Label(labelId, labelName);
        labelService.updateLabel(label);
        return CResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete/label")
    @ResponseBody
    public CommonResult deleteLabel(@RequestParam("labelId") Integer labelId){
        labelService.deleteLabelById(labelId);
        return CResultGenerator.genSuccessResult();
    }
}
