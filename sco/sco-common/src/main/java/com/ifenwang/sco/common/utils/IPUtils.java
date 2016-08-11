package com.ifenwang.sco.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 客户端地址工具.
 */
public class IPUtils
{
    /**
     * 获取客户端IP地址.
     * @param request
     * @return 客户端IP地址
     */
    public static String getRemoteAddress(HttpServletRequest request)
    {
        String addr = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(addr))
        {
            addr = request.getRemoteAddr();
        }
        return addr;
    }
}