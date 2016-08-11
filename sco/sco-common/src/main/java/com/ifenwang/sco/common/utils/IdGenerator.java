package com.ifenwang.sco.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ID生成工具.
 */
public class IdGenerator
{
    /**
     * 用于生成ID中日期部分(17位).
     */
    private static SimpleDateFormat fmtLong = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 用于生成ID中日期部分(14位).
     */
    private static SimpleDateFormat fmtMedium = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 用于生成ID中日期部分(4位).
     */
    private static SimpleDateFormat fmtShort = new SimpleDateFormat("mmss");

    /**
     * 创建登录凭证
     * 32位长度
     * @return
     */
    public static String createCredentialSql()
    {
        return String.format("C%s%s", fmtLong.format(new Date()), RandomUtils.generateString(14));
    }

    /**
     * 创建用户ID
     * 32位长度
     * @return
     */
    public static String createUserId()
    {
        return String.format("U%s%s", fmtLong.format(new Date()), RandomUtils.generateString(14));
    }

    public static void main(String[] args)
    {
        System.out.println(String.format("%s", fmtMedium.format(new Date())));
        System.out.println(String.format("%s", fmtShort.format(new Date())));
    }

}
