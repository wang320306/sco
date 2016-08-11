package com.ifenwang.sco.common.storage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.ifenwang.sco.common.storage.entity.SysParam;

/**
 * 系统参数接口
 */
public interface SysParamMapper
{
    /**
     * 添加一条数据
     * @param sysParam
     * @return
     */
    int save(SysParam sysParam);
    
    /**
     * 根据条件查询记录
     * @param params
     * @return
     */
    int queryCountByMultiple(Map<String, ?> params);
    
    /**
     * 根据条件查询记录.
     * @param rb RowBounds
     * @param params 查询条件
     * @return 符合条件的记录
     */
    List<SysParam> queryByMultiple(RowBounds rb, Map<String, ?> params);
}
