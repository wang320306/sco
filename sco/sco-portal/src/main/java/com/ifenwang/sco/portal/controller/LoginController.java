package com.ifenwang.sco.portal.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifenwang.sco.common.storage.entity.QqInfo;
import com.ifenwang.sco.portal.common.CommonResult;
import com.ifenwang.sco.portal.common.constants.SystemConstants;
import com.ifenwang.sco.portal.service.QqLoginService;
import com.ifenwang.sco.portal.service.UserService;

/**
 * 登录入口
 */
@Controller
@RequestMapping("/login")
public class LoginController
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 用户服务类
     */
    @Resource
    private UserService userService;

    /**
     * QQ登录服务类
     */
    @Resource
    private QqLoginService qqLoginService;

    /**
     * 登录入口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model)
    {
        return "login";
    }

    /**
     * PC端登录
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/signpc")
    public String signPC(HttpServletRequest request, Model model)
    {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        CommonResult result = userService.login(request, userName, password, SystemConstants.LOGINTYPE_PC);
        switch (result.getStatus())
        {
            case SUCCESS:
                // TODO 做登录后的加载操作，或者考虑用AJAX页面处理
                return "redirect:" + result.getRedirectUri();

            case INVALID_CREDENTIAL:
                model.addAttribute("emsg", "请求参数错误,请重试");
                break;

            case INVALID_CREDENTIAL_TIMEOUT:
                model.addAttribute("emsg", "请求参数错误,请重试");
                break;

            case INVALID_USERNAME_PASSWORD:
                model.addAttribute("emsg", "账号或密码错误");
                break;

            case INVALID_USER_LOCKED:
                model.addAttribute("emsg", "账号已锁定,请稍后重试");
                break;

            default:
                logger.error("bad result status.");
                break;
        }

        return "login";
    }

    /**
     * 登录入口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/signqq")
    public String signQQ(HttpServletRequest request, Model model)
    {
        String requestUrl = qqLoginService.getAuthorizationCodeUrl();
        if (null == requestUrl)
        {
            model.addAttribute("errorMsg", "QQ登录失败");
            return "login";
        }
        return "redirect:" + requestUrl;
    }

    /**
     * 授权回调
     * @param request
     * @return
     */
    @RequestMapping("/qqoauth")
    public String qqOauthBack(HttpServletRequest request, Model model)
    {
        // 获取授权CODE
        QqInfo qqinfo = qqLoginService.oauth(request);
        if (null == qqinfo)
        {
            model.addAttribute("errorMsg", "QQ登录失败");
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SystemConstants.SESSION_USER_QQ, qqinfo);
        logger.debug("qq login success.");

        CommonResult result = userService.login(request, null, null, SystemConstants.LOGINTYPE_QQ);
        switch (result.getStatus())
        {
            case SUCCESS:
                return "redirect:" + result.getRedirectUri();

            case INVALID_QQ:
                model.addAttribute("emsg", "QQ登录失败");
                break;

            default:
                logger.error("bad result status.");
                break;
        }

        return "login";
    }

}
