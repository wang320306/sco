package com.ifenwang.sco.common.storage.service;

import com.ifenwang.sco.common.storage.constants.DataTypeConstants;

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

    /**
     * QQ登录参数
     */
    public static class QqLogin
    {
        /**
         * APPID
         */
        public static String appId = DataTypeConstants.INVALID_STRING_VALUE;

        /**
         * KEY
         */
        public static String appKey = DataTypeConstants.INVALID_STRING_VALUE;
    }

    /**
     * 用户参数
     */
    public static class User
    {
        /**
         * 最大密码错误次数(超过该次数用户将被锁定).
         */
        public static int maxPasswordRetry = DataTypeConstants.INVALID_INTEGER_VALUE;

        /**
         * 密码错误次数过多后锁定用户时间(单位为分钟).
         */
        public static int passwordLockTime = DataTypeConstants.INVALID_INTEGER_VALUE;
    }

}
