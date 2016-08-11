package com.ifenwang.sco.common.storage.mapper;

import com.ifenwang.sco.common.storage.entity.UserInfo;

/**
 * 用户接口
 */
public interface UserInfoMapper
{
    /**
     * 保存一条用户信息
     * @param user
     * @return
     */
    int save(UserInfo user);

    /**
     * 修改一条用户信息
     * @param user
     * @return
     */
    int update(UserInfo user);

    /**
     * 删除一条用户信息
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    UserInfo queryByUserId(String userId);
    
    /**
     * 根据用户名称查询用户信息
     * @param userName
     * @return
     */
    UserInfo queryByUserName(String userName);

    /**
     * 根据用户QQID查询用户信息
     * @param userQQ
     * @return
     */
    UserInfo queryByQQ(String userQQ);

}