package com.ifenwang.sco.common.storage.entity;

import java.util.Date;

/**
 * 用户登录密码错误记录
 */
public class UserPasswordLog extends BaseEntity
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3698140659540128613L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 错误登录次数
     */
    private Integer loginCount = null;

    /**
     * 登录时间
     */
    private Date loginTime = null;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Integer getLoginCount()
    {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount)
    {
        this.loginCount = loginCount;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

}
