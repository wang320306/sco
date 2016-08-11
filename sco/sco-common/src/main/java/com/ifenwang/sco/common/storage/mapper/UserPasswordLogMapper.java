package com.ifenwang.sco.common.storage.mapper;

import com.ifenwang.sco.common.storage.entity.UserPasswordLog;

/**
 * 错误登录日志接口
 */
public interface UserPasswordLogMapper
{
    /**
     * 保存一条错误日志
     * @return
     */
    int save(UserPasswordLog log);

    /**
     * 修改一条错误日志
     * @param log
     * @return
     */
    int update(UserPasswordLog log);

    /**
     * 删除一条错误日志
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);

    /**
     * 根据用户ID查询日志
     * @param userId
     * @return
     */
    UserPasswordLog queryByUserId(String userId);
}
