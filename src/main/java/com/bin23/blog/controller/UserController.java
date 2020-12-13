package com.bin23.blog.controller;

import com.bin23.blog.entity.SysUser;
import com.bin23.blog.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private SysUserServiceImpl sysUserService;

    @RequestMapping("/")
    public String showIndex() {
        // 获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        return "index";
    }

    @GetMapping("/userLogin")
    public String toLoginPage(){
        return "login";
    }

    /**
     * @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
     * @return
     */
    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

    @GetMapping("/toRegisterPage")
    public String toRegisterPage(){
        return "register";
    }

    @PostMapping("/userRegister")
    public String userRegister(SysUser sysUser){
        String password = sysUser.getPassword();
        BCryptPasswordEncoder bcryptPasswordEncoder  = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(password);
        sysUser.setPassword(hashPass);
        int i = sysUserService.sysUserRegister(sysUser);
        logger.info("注册返回值为1即成功注册：" + i);
        return "index";
    }
}
