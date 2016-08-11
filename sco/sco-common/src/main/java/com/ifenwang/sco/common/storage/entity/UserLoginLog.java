package com.ifenwang.sco.common.storage.entity;

import java.util.Date;

/**
 * 用户登录日志记录
 */
public class UserLoginLog extends BaseEntity
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7413429367430155695L;

    /**
     * PC登录
     */
    public static final String LOGINTYPE_PC = "PC";

    /**
     * QQ登录
     */
    public static final String LOGINTYPE_QQ = "QQ";

    /**
     * 登录ID
     */
    private Integer logId = null;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 登录时间
     */
    private Date loginTime = null;

    /**
     * 登录IP
     */
    private String ipAddress = null;

    /**
     * 登录信息
     */
    private String userAgent = null;

    /**
     * 登录类型
     */
    private String loginType = null;

    public Integer getLogId()
    {
        return logId;
    }

    public void setLogId(Integer logId)
    {
        this.logId = logId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public String getLoginType()
    {
        return loginType;
    }

    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }

}
