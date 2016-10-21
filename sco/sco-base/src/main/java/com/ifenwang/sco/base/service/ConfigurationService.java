package com.ifenwang.sco.base.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifenwang.sco.base.common.DataTypeConstants;
import com.ifenwang.sco.base.entity.SysParam;
import com.ifenwang.sco.base.exception.SeverityException;
import com.ifenwang.sco.base.mapper.SysParamMapper;

/**
 * 系统参数服务.
 */
@Service
public class ConfigurationService
{
    /**
     * logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    /**
     * sysParamMapper实例.
     */
    @Resource
    private SysParamMapper sysParamMapper;

    /**
     * 参数缓存，用于通过名称查找.
     */
    private Map<String, String> parameterMap;

    /**
     * 初始化系统参数
     */
    @PostConstruct
    public void initialize()
    {
        logger.debug("Initializing system parameters...");
        parameterMap = new HashMap<String, String>();
        List<SysParam> params = sysParamMapper.queryByMultiple(RowBounds.DEFAULT, null);
        for (SysParam param : params)
        {
            // 保存到Configuration中
            parseParameter(param);

            // 保存到Map
            parameterMap.put(param.getParamName().toUpperCase(), param.getParamValue());
        }

        // 检查系统参数是否完全设置
        checkParameters();

        // 成功
        logger.debug("System parameter successfully loaded.");
    }

    /**
     * 检查系统参数是否全部设置.
     * @throws SeverityException 检查时发生错误或者系统参数没有设置
     */
    private void checkParameters() throws SeverityException
    {
        try
        {
            logger.debug("Checking system parameters.");
            for (Class<?> clazz : Configuration.class.getClasses())
            {
                for (Field field : clazz.getFields())
                {
                    String type = field.getType().getSimpleName().toUpperCase();
                    if (type.equals("INT") && field.getInt(null) == DataTypeConstants.INVALID_INTEGER_VALUE)
                    {
                        String message = "System parameter not set: " + clazz.getSimpleName() + "." + field.getName();
                        logger.error(message);
                        throw new SeverityException(message);
                    }
                    else if (type.equals("STRING"))
                    {
                        String value = (String) field.get(null);
                        if (value == null || value.equals(DataTypeConstants.INVALID_STRING_VALUE))
                        {
                            String message = "System parameter not set: " + clazz.getSimpleName() + "."
                                    + field.getName();
                            logger.error(message);
                            throw new SeverityException(message);
                        }
                    }
                }
            }
            logger.debug("System parameter check succeeded.");
        }
        catch (IllegalAccessException e)
        {
            logger.error("Failed to check system parameters.", e);
            throw new SeverityException("Failed to check system parameters.", e);
        }
    }

    /**
     * 处理一个系统参数.
     * @param param 系统参数
     */
    private void parseParameter(SysParam param)
    {
        String[] paths = param.getParamName().split("\\.");

        // 查找字段.
        Class<?> clazz = Configuration.class;
        clazz = findInnerClass(clazz, paths[0]);
        if (clazz == null)
        {
            logger.error("Failed to set parameter: {}", param.getParamName());
            throw new SeverityException("Failed to load system parameters.");
        }

        // 设置字段值.
        try
        {
            logger.debug("Loading parameter: {} --> {}", param.getParamName(), param.getParamValue());

            // 将第一个字母转为小写
            String fieldName = paths[1];
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

            // 设置字段值
            Field field = clazz.getField(fieldName);
            switch (param.getDataType())
            {
                case DataTypeConstants.DATATYPE_INTEGER:
                    int intval = Integer.parseInt(param.getParamValue());
                    field.setInt(null, intval);
                    break;

                case DataTypeConstants.DATATYPE_STRING:
                    field.set(null, param.getParamValue());
                    break;

                default:
                    logger.error("Bad parameter type: {}", param.getDataType());
            }
        }
        catch (Exception e)
        {
            logger.error("Failed to set parameter: {}", param.getParamName());
            throw new SeverityException("Failed to load system parameters.", e);
        }
    }

    /**
     * 查找一个内部类.
     * @param outer 外部类.
     * @param name  内部类名称.
     * @return 内部类反射类型.
     */
    private Class<?> findInnerClass(Class<?> outer, String name)
    {
        for (Class<?> c : outer.getDeclaredClasses())
        {
            if (c.getSimpleName().equals(name))
            {
                return c;
            }
        }
        return null;
    }

    /**
     * 获取参数值.
     * @param name 参数名，如User.minUsernameLength，不区分大小写
     * @return 参数值
     */
    public String findParameterValue(String name)
    {
        if (name == null)
        {
            return null;
        }
        return parameterMap.get(name.toUpperCase());
    }

}
