package com.ifenwang.sco.common.storage.entity;

import java.util.Date;

/**
 * 用户信息
 */
public class UserInfo extends BaseEntity
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8078731772220362592L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 用户名称
     */
    private String userName = null;

    /**
     * 密码
     */
    private String passwordHash = null;

    /**
     * 昵称
     */
    private String nickName = null;

    /**
     * 用户性别
     */
    private String userSex = null;

    /**
     * 生日
     */
    private String birthday = null;

    /**
     * 邮箱
     */
    private String userEmail = null;

    /**
     * 手机
     */
    private String userPhone = null;

    /**
     * 城市
     */
    private String city = null;

    /**
     * 省份
     */
    private String province = null;

    /**
     * 国家
     */
    private String country = null;

    /**
     * 头像
     */
    private String headImage = null;

    /**
     * 注册时间
     */
    private Date registDate = null;

    /**
     * 上次登录时间
     */
    private Date loginDate = null;

    /**
     * 标志位
     */
    private String flags = null;

    /**
     * 登录类型
     */
    private String loginType = null;

    /**
     * 用户QQ号
     */
    private String userQQ = null;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getUserSex()
    {
        return userSex;
    }

    public void setUserSex(String userSex)
    {
        this.userSex = userSex;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getHeadImage()
    {
        return headImage;
    }

    public void setHeadImage(String headImage)
    {
        this.headImage = headImage;
    }

    public Date getRegistDate()
    {
        return registDate;
    }

    public void setRegistDate(Date registDate)
    {
        this.registDate = registDate;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public String getFlags()
    {
        return flags;
    }

    public void setFlags(String flags)
    {
        this.flags = flags;
    }

    public String getLoginType()
    {
        return loginType;
    }

    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }

    public String getUserQQ()
    {
        return userQQ;
    }

    public void setUserQQ(String userQQ)
    {
        this.userQQ = userQQ;
    }

}