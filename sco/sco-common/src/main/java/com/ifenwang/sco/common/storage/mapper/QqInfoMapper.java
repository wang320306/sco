package com.ifenwang.sco.common.storage.mapper;

import com.ifenwang.sco.common.storage.entity.QqInfo;

/**
 * 令牌
 */
public interface QqInfoMapper
{
    /**
     * 保存一条QQ登录信息
     * @param qqInfo
     * @return
     */
    int save(QqInfo qqInfo);

    /**
     * 修改一条QQ登录信息
     * @param qqInfo
     * @return
     */
    int update(QqInfo qqInfo);

    /**
     * 根据用户ID查询QQ登录信息
     * @param openId
     * @return
     */
    QqInfo queryByOpenId(String openId);

}