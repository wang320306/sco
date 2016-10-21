package com.ifenwang.sco.base.common;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体类基类
 */
public class BaseEntity implements Serializable, Cloneable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2682528294162549046L;

    /**
     * 需要排除的以get开头的方法，如getClass()，这些方法不作为属性处理.
     */
    private static Map<String, Object> excludedGetters = new HashMap<String, Object>();

    /**
     * 初始化.
     */
    static
    {
        excludedGetters.put("getClass", new Object());
    }

    /**
     * 判断是否所有字段都为null.
     * @return 是否所有字段都为null
     */
    public boolean isNull()
    {
        try
        {
            for (Method m : this.getClass().getMethods())
            {
                /*
                 * 排除不已getter开头的方法、getClass等非属性方法以及包含参数的get方法
                 * 这些方法不作为属性处理
                 */
                if (excludedGetters.containsKey(m.getName()))
                {
                    continue;
                }
                if (!m.getName().startsWith("get"))
                {
                    continue;
                }
                if (m.getParameterTypes().length > 0)
                {
                    continue;
                }

                // 判断字段是否为空
                Object value = m.invoke(this);
                if (value != null)
                {
                    return false;
                }
            }
            return true;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to check null from base entity.", e);
        }
    }

    @Override
    public String toString()
    {
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append(this.getClass().getSimpleName());
            sb.append(" { ");
            Method[] methods = this.getClass().getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method m = methods[i];

                // 排除非getter方法
                if (!isValidGetter(m))
                {
                    continue;
                }

                // 处理字段
                if (i != 0)
                {
                    sb.append(", ");
                }
                String name = m.getName().substring(3);
                Object value = m.invoke(this);
                sb.append(name);
                sb.append("=");
                sb.append(value);
            }
            sb.append(" }");
            return sb.toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to get field values.", e);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        try
        {
            // 被判断的对象为null，不相等
            if (obj == null)
            {
                return false;
            }

            // 不同类型，不相等
            if (!obj.getClass().getName().equals(this.getClass().getName()))
            {
                return false;
            }

            // 判断属性
            Method[] methods = this.getClass().getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method m = methods[i];

                // 排除非getter方法
                if (!isValidGetter(m))
                {
                    continue;
                }

                // 获取字段值
                Object value1 = m.invoke(this);
                Object value2 = m.invoke(obj);

                // 比较字段
                if (value1 == null && value2 == null)
                {
                    continue;
                }
                if (value1 != null && value2 == null || value1 == null && value2 != null)
                {
                    return false;
                }
                if (!value1.equals(value2))
                {
                    return false;
                }
            }
            return true;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to get field values.", e);
        }
    }

    @Override
    public Object clone()
    {
        try
        {
            // 创建新实例
            BaseEntity newEntity = this.getClass().newInstance();

            // 复制属性
            Method[] methods = this.getClass().getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method getter = methods[i];

                // 排除非getter方法
                if (!isValidGetter(getter))
                {
                    continue;
                }

                // 获取字段值
                Object value = getter.invoke(this);

                // 获取setter方法
                String setterName = "set" + getter.getName().substring(3);
                Method setter = this.getClass().getMethod(setterName, getter.getReturnType());

                // value为null时特殊处理
                if (value == null)
                {
                    setter.invoke(newEntity, (Object) null);
                    continue;
                }

                // 设置字段值
                if (value instanceof Cloneable)
                {
                    Method cloneMethod = value.getClass().getMethod("clone");
                    value = cloneMethod.invoke(value);
                }
                setter.invoke(newEntity, value);
            }
            return newEntity;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to get field values.", e);
        }
    }

    @Override
    public int hashCode()
    {
        try
        {
            // Hash结果
            int hash = 0x12345678;

            // 复制属性
            Method[] methods = this.getClass().getMethods();
            for (int i = 0; i < methods.length; i++)
            {
                Method getter = methods[i];

                // 排除非getter方法
                if (!isValidGetter(getter))
                {
                    continue;
                }

                // 获取字段值
                Object value = getter.invoke(this);
                if (value != null)
                {
                    hash = hash & value.hashCode();
                }
            }
            return hash;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to calculate hash code.", e);
        }
    }

    /**
     * 判断一个方法是否为一个属性的getter方法.
     * @param m 需要判断的方法
     * @return 是否为getter方法
     */
    private boolean isValidGetter(Method m)
    {
        // 不以get开头的方法不是getter方法
        if (!m.getName().startsWith("get"))
        {
            return false;
        }

        // 包含参数的get方法不是getter方法
        if (m.getParameterTypes().length > 0)
        {
            return false;
        }

        // 部分特殊的get方法不是属性，如getClass()等
        // 修改静态初始化增加排除的方法列表
        if (excludedGetters.containsKey(m.getName()))
        {
            return false;
        }

        return true;
    }

}
