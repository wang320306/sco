package com.ifenwang.sco.portal.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ifenwang.sco.common.storage.service.Configuration;
import com.ifenwang.sco.portal.common.constants.SystemConstants;

/**
 * 登陆工具类
 */
public class LoginUtils
{
    /**
     * 获取跳转路径
     * @param request
     * @return
     */
    public static String getRedirectUrl(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String redirect = (String) session.getAttribute(SystemConstants.SESSION_REDIRECTURL);
        if (StringUtils.isEmpty(redirect))
        {
            return Configuration.Global.portalUrl;
        }
        return redirect;
    }
}
