package com.ifenwang.sco.portal.utils;

import org.slf4j.Logger;

/**
 * 日志工具类
 */
public class LogUtils
{
    public static void baseLog(Logger logger, String userId, String msg)
    {
        logger.debug("[{}]:[{}]", userId, msg);
    }

    public static void baseErrorLog(Logger logger, String userId, String errorMsg)
    {
        logger.error("[{}]:[{}]", userId, errorMsg);
    }
}
