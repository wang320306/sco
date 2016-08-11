package com.ifenwang.sco.portal.common;

import java.io.Serializable;

import com.ifenwang.sco.common.storage.entity.Credential;
import com.ifenwang.sco.common.storage.entity.QqInfo;

/**
 * 公共结果封装
 */
public class CommonResult implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4455462597356304876L;

    public CommonResult()
    {
    }

    public CommonResult(CommonResultStatus status)
    {
        this.status = status;
    }

    //------------------------------------------------------------------

    /**
     * 错误码码
     */
    private String errorCode = null;

    /**
     * 错误信息
     */
    private String errorMsg = null;

    /**
     * 状态
     */
    private CommonResultStatus status = null;

    /**
     * 登录凭证
     */
    private Credential credential = null;

    /**
     * QQ信息
     */
    private QqInfo qqInfo = null;

    /**
     * 跳转链接
     */
    private String redirectUri = null;

    //------------------------------------------------------------------

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public CommonResultStatus getStatus()
    {
        return status;
    }

    public void setStatus(CommonResultStatus status)
    {
        this.status = status;
    }

    public String getRedirectUri()
    {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri)
    {
        this.redirectUri = redirectUri;
    }

    public Credential getCredential()
    {
        return credential;
    }

    public void setCredential(Credential credential)
    {
        this.credential = credential;
    }

    public QqInfo getQqInfo()
    {
        return qqInfo;
    }

    public void setQqInfo(QqInfo qqInfo)
    {
        this.qqInfo = qqInfo;
    }

}
