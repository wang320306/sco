package com.ifenwang.sco.portal.common;

/**
 * 状态
 */
public enum CommonResultStatus
{

    /**
     * 成功.
     */
    SUCCESS("0000"),

    /**
     * 登录：用户名或密码错误
     */
    INVALID_USERNAME_PASSWORD("L0001"),

    /**
    * 错误密码导致用户锁定.
    */
    INVALID_USER_LOCKED("L0002"),

    /**
    * 参数错误：凭证错误
    */
    INVALID_CREDENTIAL("L0003"),

    /**
    * 凭证失效
    */
    INVALID_CREDENTIAL_TIMEOUT("L0004"),
    
    /**
    * QQ登录失败
    */
    INVALID_QQ("L0005");

    //--------------------------------------------------------------------

    /**
     * 错误码
     */
    private String errorCode = null;

    public String getErrorCode()
    {
        return errorCode;
    }

    private CommonResultStatus(String code)
    {
        this.errorCode = code;
    }

}
