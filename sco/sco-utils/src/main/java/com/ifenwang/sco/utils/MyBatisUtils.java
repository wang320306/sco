package com.ifenwang.sco.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * MyBatis工具类.
 */
public class MyBatisUtils
{
    /**
     * 空map.
     */
    public static final Map<String, ?> EMPTY_MAP = new TreeMap<String, Object>();

    /**
     * 构造参数Map.
     * buildParameterMap("name1", value1, "name2", value2, ...) 
     * @param objs 参数名称和值
     * @return     参数Map，用于传递给MyBatis Mapper
     */
    public static Map<String, ?> buildParameterMap(Object...objs)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < objs.length / 2; i++)
        {
            String key = (String) objs[i * 2];
            Object value = objs[i * 2 + 1];
            if (value != null)
            {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 构造参数Map.
     * buildParameterMap("name1", value1, "name2", value2, ...) 
     * @param objs 参数名称和值
     * @return     参数Map，用于传递给MyBatis Mapper
     */
    public static Map<String, ?> buildQueryParameterMap(Object...objs)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < objs.length / 2; i++)
        {
            String key = (String) objs[i * 2];
            Object value = objs[i * 2 + 1];
            if (value != null && value instanceof String && ((String) value).isEmpty())
            {
                value = null;
            }
            map.put(key, value);
        }
        return map;
    }
}
