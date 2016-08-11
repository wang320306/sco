package com.ifenwang.sco.common.storage.entity;

import java.util.Date;

/**
 * 登录凭证
 */
public class Credential extends BaseEntity
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8795223641230417520L;

    /**
     * 未验证.
     */
    public static final int STATUS_CREATE = 0;
    
    /**
     * 验证处理中.
     */
    public static final int STATUS_DOING = 1;

    /**
     * 已验证.
     */
    public static final int STATUS_APPROVED = 2;

    /**
     * 凭证
     */
    private String credential = null;

    /**
     * 状态
     */
    private Integer checkStatus = null;

    /**
     * 创建时间
     */
    private Date createDate = null;

    /**
     * 第三方登录编号
     */
    private String oauthCode = null;

    public String getCredential()
    {
        return credential;
    }

    public void setCredential(String credential)
    {
        this.credential = credential;
    }

    public Integer getCheckStatus()
    {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus)
    {
        this.checkStatus = checkStatus;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getOauthCode()
    {
        return oauthCode;
    }

    public void setOauthCode(String oauthCode)
    {
        this.oauthCode = oauthCode;
    }

}
