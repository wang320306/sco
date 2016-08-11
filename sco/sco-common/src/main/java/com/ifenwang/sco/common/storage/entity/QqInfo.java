package com.ifenwang.sco.common.storage.entity;

import java.util.Date;

/**
 * QQ登录信息
 */
public class QqInfo extends BaseEntity
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8795223641230417520L;

    /**
     * 用户标识
     */
    private String openId = null;

    /**
     * 昵称
     */
    private String nickName = null;

    /**
     * 空间头像30x30地址
     */
    private String figureUrl = null;

    /**
     * 空间头像50x50地址
     */
    private String figureUrl1 = null;

    /**
     * 空间头像100x100地址
     */
    private String figureUrl2 = null;

    /**
     * QQ头像40x40地址
     */
    private String figureQqUrl = null;

    /**
     * QQ头像100x100地址
     */
    private String figureQqUrl2 = null;

    /**
     * 性别
     */
    private String gender = null;

    /**
     * 省份
     */
    private String province = null;

    /**
     * 城市
     */
    private String city = null;

    /**
     * 令牌
     */
    private String token = null;

    /**
     * 有效时间(秒)
     */
    private String expiresIn = null;

    /**
     * 刷新令牌
     */
    private String refreshToken = null;

    /**
     * 令牌更新时间
     */
    private Date updateDate = null;

    public String getOpenId()
    {
        return openId;
    }

    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getFigureUrl()
    {
        return figureUrl;
    }

    public void setFigureUrl(String figureUrl)
    {
        this.figureUrl = figureUrl;
    }

    public String getFigureUrl1()
    {
        return figureUrl1;
    }

    public void setFigureUrl1(String figureUrl1)
    {
        this.figureUrl1 = figureUrl1;
    }

    public String getFigureUrl2()
    {
        return figureUrl2;
    }

    public void setFigureUrl2(String figureUrl2)
    {
        this.figureUrl2 = figureUrl2;
    }

    public String getFigureQqUrl()
    {
        return figureQqUrl;
    }

    public void setFigureQqUrl(String figureQqUrl)
    {
        this.figureQqUrl = figureQqUrl;
    }

    public String getFigureQqUrl2()
    {
        return figureQqUrl2;
    }

    public void setFigureQqUrl2(String figureQqUrl2)
    {
        this.figureQqUrl2 = figureQqUrl2;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getExpiresIn()
    {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn)
    {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

}
