package com.ifenwang.sco.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.ifenwang.sco.base.entity.SysDomain;

/**
 * SysDomain 表字段取值域定义信息.
 */
public interface SysDomainMapper
{
    /**
     * 新建一个 定义信息.
     * @param sysDomain 定义信息
     * @return 受影响记录数
     */
    int save(SysDomain sysDomain);

    /**
     * 更新定义信息.
     * @param sysDomain 定义信息
     * @return 受影响记录数
     */
    int update(SysDomain sysDomain);

    /**
     * 删除定义信息.
     * @param sysDomain 定义信息
     * @return 受影响记录数
     */
    int delete(SysDomain sysDomain);

    /**
     * 根据字段名获取 字段取值定义信息.
     * @param params 字段
     *              tableName 选填
     *              fieldName 选填
     *              domain 选填
     * @return 字段的取值及定义信息
     */
    List<SysDomain> queryListByMap(Map<String, ?> params);

    /**
     * 根据查询条件获取 定义信息记录数.
     * @param params 查询参数
     *              tableName 选填 模糊查询
     *              fieldName 选填 模糊查询
     *              domain 选填 模糊查询
     * @return 记录数
     */
    int queryCountByMultiple(Map<String, ?> params);

    /**
     * 根据查询条件获取 定义信息.
     * @param rowBounds RowBounds
     * @param params 查询参数
     *              tableName 选填 模糊查询
     *              fieldName 选填 模糊查询
     *              domain 选填 模糊查询
     * @return 定义信息
     */
    List<SysDomain> queryByMultiple(RowBounds rowBounds, Map<String, ?> params);

}
