package com.bin23.blog.controller.admin;

import com.bin23.blog.entity.result.CResultGenerator;
import com.bin23.blog.entity.result.CommonResult;
import com.bin23.blog.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin")
public class UserManageController {
    private static final Boolean IS_BAN = true;
    private static final Boolean NOT_BAN = false;

    @Resource
    private SysUserService userService;

    @GetMapping("/user/ban")
    @ResponseBody
    public CommonResult banUser(@RequestParam("userId") Integer userId) {
        userService.updateUserIsBan(userId, IS_BAN);
        return CResultGenerator.genSuccessResult();
    }

    @GetMapping("/user/unban")
    @ResponseBody
    public CommonResult unBanUser(@RequestParam("userId") Integer userId) {
        userService.updateUserIsBan(userId, NOT_BAN);
        return CResultGenerator.genSuccessResult();
    }
}
