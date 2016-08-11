package com.ifenwang.sco.common.storage.tablecache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ifenwang.sco.common.storage.tablecache.tables.SysDomainCache;

/**
 * 表缓存管理器.
 */
public class TableCacheManager implements ApplicationContextAware
{
    /**
     * logger.
     */
    private static Logger logger = LoggerFactory.getLogger(TableCacheManager.class);

    /**
     * 注册的缓存.
     */
    private Map<String, CacheInfo> cachedTables = new HashMap<String, CacheInfo>();

    /**
     * Spring Application Context.
     */
    private ApplicationContext applicationContext;

    /**
     * 缓存刷新线程.
     */
    private Thread updateThread;

    /**
     * 缓存信息.
     */
    private class CacheInfo
    {
        /**
         * 缓存ID.
         */
        private String cacheId;

        /**
         * 缓存数据.
         */
        private TableCache cache;

        /**
         * 最后更新时间.
         */
        private Date lastUpdate;

        /**
         * 刷新间隔(分钟).
         */
        private int updateInterval;

        public String getCacheId()
        {
            return cacheId;
        }

        public void setCacheId(String cacheId)
        {
            this.cacheId = cacheId;
        }

        public TableCache getCache()
        {
            return cache;
        }

        public void setCache(TableCache cache)
        {
            this.cache = cache;
        }

        public Date getLastUpdate()
        {
            return lastUpdate;
        }

        public void setLastUpdate(Date lastUpdate)
        {
            this.lastUpdate = lastUpdate;
        }

        public int getUpdateInterval()
        {
            return updateInterval;
        }

        public void setUpdateInterval(int updateInterval)
        {
            this.updateInterval = updateInterval;
        }
    }

    /**
     * 初始化.
     */
    public void initialize()
    {
        logger.debug("Initi");

        // 注册缓存
        registerCache(SysDomainCache.class, 60 * 24);

        // 启动时加载全部
        reloadAll();

        // 启动刷新线程
        updateThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                updateThreadProc();
            }
        });
        updateThread.start();
    }

    /**
     * 清理.
     */
    @PreDestroy
    public void destroy()
    {
        if (updateThread != null)
        {
            updateThread.interrupt();
        }
    }

    /**
     * 刷新线程处理.
     */
    public void updateThreadProc()
    {
        try
        {
            logger.debug("Cache update thread started.");

            // 等待1分钟后开始
            Thread.sleep(1000 * 60);

            // 循环刷新
            while (true)
            {
                // 处理所有缓存
                Date now = new Date();
                for (Entry<String, CacheInfo> cache : cachedTables.entrySet())
                {
                    CacheInfo ci = cache.getValue();
                    if (now.getTime() - ci.getLastUpdate().getTime() > ci.getUpdateInterval() * 1000 * 60)
                    {
                        logger.debug("Reloading table cache: " + ci.getCacheId());
                        ci.getCache().reload();
                        ci.setLastUpdate(now);
                    }
                }

                // 等待1分钟
                Thread.sleep(1000 * 60);
            }
        }
        catch (InterruptedException e)
        {
            logger.debug("Cache update thread stopped.");
        }
    }

    /**
     * 刷新所有表缓存数据.
     */
    public void reloadAll()
    {
        for (Entry<String, CacheInfo> cache : cachedTables.entrySet())
        {
            logger.debug("Reloading cache: " + cache.getKey());
            cache.getValue().getCache().reload();
        }
    }

    /**
     * 刷新指定的表缓存.
     * @param cacheClass 缓存类名
     */
    public void reload(Class<? extends TableCache> cacheClass)
    {
        String cacheId = "TABLECACHE_" + cacheClass.getSimpleName();
        logger.debug("Reloading cache: " + cacheId);
        TableCache cache = cachedTables.get(cacheId).getCache();
        if (cache != null)
        {
            cache.reload();
        }
    }

    /**
     * 注册缓存.
     * @param cacheClass 缓存类(必须继承TableCache)
     * @param updateInterval 刷新间隔(分钟)
     */
    private void registerCache(Class<? extends TableCache> cacheClass, int updateInterval)
    {
        TableCache cache = applicationContext.getBean(cacheClass);
        String cacheId = "TABLECACHE_" + cacheClass.getSimpleName();
        CacheInfo ci = new CacheInfo();
        ci.setCache(cache);
        ci.setCacheId(cacheId);
        ci.setLastUpdate(new Date());
        ci.setUpdateInterval(updateInterval);
        cachedTables.put(cacheId, ci);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
}
