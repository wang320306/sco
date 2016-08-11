package com.ifenwang.sco.common.storage.mapper;

import com.ifenwang.sco.common.storage.entity.UserLoginLog;

/**
 * 用户登录日志接口
 */
public interface UserLoginLogMapper
{
    /**
     * 保存一条用户登录日志信息
     * @param userLoginLog
     * @return
     */
    int save(UserLoginLog userLoginLog);
}
