package com.ifenwang.sco.base.entity;

import com.ifenwang.sco.base.common.BaseEntity;

/**
 * 系统参数
 */
public class SysParam extends BaseEntity
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9188807740508561818L;

    /**
     * 参数名称
     */
    private String paramName = null;

    /**
     * 分类
     */
    private String paramType = null;

    /**
     * 标题
     */
    private String paramTitle = null;

    /**
     * 值
     */
    private String paramValue = null;

    /**
     * 类型
     */
    private Integer dataType = null;

    public String getParamName()
    {
        return paramName;
    }

    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }

    public String getParamType()
    {
        return paramType;
    }

    public void setParamType(String paramType)
    {
        this.paramType = paramType;
    }

    public String getParamTitle()
    {
        return paramTitle;
    }

    public void setParamTitle(String paramTitle)
    {
        this.paramTitle = paramTitle;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public Integer getDataType()
    {
        return dataType;
    }

    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

}
