package com.ifenwang.sco.base.entity;

import com.ifenwang.sco.base.common.BaseEntity;

/**
 * 系统表字段取值定义类.
 */
public class SysDomain extends BaseEntity
{
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1750207653399754378L;

    /**
     * 表名
     */
    private String tableName = null;

    /**
     * 字段名
     */
    private String fieldName = null;

    /**
     * 字段取值
     */
    private String domain = null;

    /**
     * 值域定义
     */
    private String notes = null;

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
