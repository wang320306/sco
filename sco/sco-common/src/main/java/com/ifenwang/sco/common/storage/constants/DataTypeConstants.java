package com.ifenwang.sco.common.storage.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据类型常量.
 */
public interface DataTypeConstants
{
    /**
     * 表示参数类型为整型.
     */
    public static final int DATATYPE_INTEGER = 1;

    /**
     * 表示参数类型为字符串.
     */
    public static final int DATATYPE_STRING = 2;

    /**
     * 表示一个无效的整型值.
     */
    public static final int INVALID_INTEGER_VALUE = Integer.MIN_VALUE;

    /**
     * 表示一个无效的字符串值.
     */
    public static final String INVALID_STRING_VALUE = StringUtils.EMPTY;
}
