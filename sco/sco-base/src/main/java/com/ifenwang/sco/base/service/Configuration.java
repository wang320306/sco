package com.ifenwang.sco.base.service;

import com.ifenwang.sco.base.common.DataTypeConstants;

/**
 * 系统参数定义
 */
public class Configuration
{
    /**
     * 公共参数
     */
    public static class Global
    {
        /**
         * 服务器默认访问域名
         */
        public static String portalUrl = DataTypeConstants.INVALID_STRING_VALUE;

        /**
         * 电子邮件验证表达式
         */
        public static String emailRegex = DataTypeConstants.INVALID_STRING_VALUE;
    }

}
