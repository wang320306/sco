package com.ifenwang.sco.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifenwang.sco.portal.service.UserService;

/**
 * 用户入口
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    /**
     * 用户服务类
     */
    @Resource
    private UserService userService;

    

}
