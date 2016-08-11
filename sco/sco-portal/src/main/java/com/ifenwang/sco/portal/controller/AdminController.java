package com.ifenwang.sco.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理入口
 */
@Controller
@RequestMapping("/admin")
public class AdminController
{
    /**
     * 后台首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model)
    {
        return "/admin/home";
    }
}