package com.ifenwang.sco.common.utils;

import java.util.Random;

/**
 * 用于生成随机数据的工具类.
 */
public class RandomUtils
{
    /**
     * 默认随机内容
     */
    private static final String DEFAULT_CONSTANT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 从指定的字符中生成随机字符串.
     * @param from   随机字符(将从这些字符中生成随机字符串)
     * @param length 生成的随机字符串长度
     * @return       生成的随机字符串
     */
    public static String generateString(String from, int length)
    {
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < length; i++)
        {
            sb.append(from.charAt(rnd.nextInt(from.length())));
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串. (A-Za-z0-9)
     * @param length 需要生成的随机字符串长度
     * @return       生成的随机字符串
     */
    public static String generateString(int length)
    {
        return generateString(DEFAULT_CONSTANT, length);
    }
}
