package com.ifenwang.sco.base.tablecache.tables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifenwang.sco.base.entity.SysDomain;
import com.ifenwang.sco.base.mapper.SysDomainMapper;
import com.ifenwang.sco.base.tablecache.TableCache;
import com.ifenwang.sco.utils.MyBatisUtils;

/**
 * 系统字段定义信息缓存.
 */
@Service("sysDomainCache")
public class SysDomainCache implements TableCache
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(SysDomainCache.class);

    /**
     * SysDomainMapper实例.
     */
    @Resource
    private SysDomainMapper sysDomainMapper;

    /**
     * 缓存.
     */
    private static Map<String, List<SysDomain>> dataLists;

    /**
     * 缓存.
     */
    private static Map<String, SysDomain> datas;

    @Override
    public synchronized void reload()
    {
        logger.debug("Reloading table cache [SysDomain] to memory.");

        datas = new HashMap<String, SysDomain>();
        dataLists = new HashMap<String, List<SysDomain>>();

        List<SysDomain> all = sysDomainMapper.queryByMultiple(RowBounds.DEFAULT, MyBatisUtils.EMPTY_MAP);
        for (SysDomain domain : all)
        {
            // 保存到datas
            String key = domain.getTableName().toUpperCase() + "##" + domain.getFieldName().toUpperCase() + "##"
                    + domain.getDomain().toUpperCase();
            datas.put(key, domain);

            // 保存到dataLists
            key = domain.getTableName().toUpperCase() + "##" + domain.getFieldName().toUpperCase();
            List<SysDomain> list = dataLists.get(key);
            if (list == null)
            {
                list = new ArrayList<SysDomain>();
                dataLists.put(key, list);
            }
            list.add(domain);
        }
    }

    /**
     * 根据表名,字段名 来获取 值和定义 集合.
     * @param tableName 表名
     * @param fieldName 字段名
     * @return 字段值和定义 list
     */
    public synchronized List<SysDomain> queryDomains(String tableName, String fieldName)
    {
        String key = tableName.toUpperCase() + "##" + fieldName.toUpperCase();
        return dataLists.get(key);
    }

    /**
     * 根据类,字段名  来获取 值和定义 集合.
     * @param clazz 类
     * @param fieldName 字段名
     * @return 字段值和定义 list
     */
    public synchronized List<SysDomain> queryDomains(Class<?> clazz, String fieldName)
    {
        return queryDomains(clazz.getSimpleName(), fieldName);
    }

    /**
     * 根据表名,字段名  以及 指定字段 值 得到  值域定义.
     * @param tableName 表名
     * @param fieldName 字段名
     * @param domain    字段取值
     * @return          值域定义
     */
    public synchronized String queryDomain(String tableName, String fieldName, String domain)
    {
        String key = tableName.toUpperCase() + "##" + fieldName.toUpperCase() + "##" + domain.toUpperCase();
        SysDomain dom = datas.get(key);
        if (dom != null)
        {
            return dom.getNotes();
        }
        return "";
    }

    /**
     * 根据类,字段名  以及 指定字段 值 得到  值域定义.
     * @param clazz 类
     * @param fieldName 字段名 
     * @param domain  指定字段值
     * @return 值域定义
     */
    public synchronized String queryDomain(Class<?> clazz, String fieldName, String domain)
    {
        return queryDomain(clazz.getSimpleName(), fieldName, domain);
    }

}
