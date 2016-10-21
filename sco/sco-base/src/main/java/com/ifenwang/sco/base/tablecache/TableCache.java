package com.ifenwang.sco.base.tablecache;

/**
 * 内存缓存表.
 */
public interface TableCache
{
    /**
     * 重新从数据库加载数据.
     */
    void reload();
}
