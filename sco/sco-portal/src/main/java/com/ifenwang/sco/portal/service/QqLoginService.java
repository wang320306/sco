package com.ifenwang.sco.portal.service;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifenwang.sco.common.storage.entity.Credential;
import com.ifenwang.sco.common.storage.entity.QqInfo;
import com.ifenwang.sco.common.storage.mapper.CredentialMapper;
import com.ifenwang.sco.common.storage.mapper.QqInfoMapper;
import com.ifenwang.sco.common.storage.mapper.UserInfoMapper;
import com.ifenwang.sco.common.storage.mapper.UserLoginLogMapper;
import com.ifenwang.sco.common.utils.HttpUtils;
import com.ifenwang.sco.common.utils.IdGenerator;
import com.ifenwang.sco.common.utils.QqLoginUtils;

/**
 * QQ登录服务类
 */
@Service("qqLoginService")
public class QqLoginService
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(QqLoginService.class);

    /**
     * credentialService
     */
    @Resource
    private CredentialMapper credentialMapper;

    /**
     * qqTokenInfoMapper
     */
    @Resource
    private QqInfoMapper qqInfoMapper;

    /**
     * userInfoMapper
     */
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * userLoginLogMapper
     */
    @Resource
    private UserLoginLogMapper userLoginLogMapper;

    /**
     * 创建QQ登录获取Authorization Code的URL
     * 用于页面redirect
     * @param paramC 凭证
     * @return redirectUrl
     */
    public String getAuthorizationCodeUrl()
    {
        Credential c = new Credential();
        c.setCredential(IdGenerator.createCredentialSql());
        c.setCheckStatus(Credential.STATUS_CREATE);
        c.setCreateDate(new Date());
        credentialMapper.save(c);

        String uri = QqLoginUtils.getAuthorizeURL(c.getCredential());
        return uri;
    }

    /**
     * 解析响应斌并获取QQ信息
     * @param request
     * @return
     */
    public QqInfo oauth(HttpServletRequest request)
    {
        // 解析Authorization响应
        Credential c = parseAuthorizationCode(request);
        if (null == c)
        {
            return null;
        }

        // 获取accessToken和openid
        QqInfo qqinfo = getTokenAndOpenId(c.getCredential());
        if (null == qqinfo)
        {
            return null;
        }

        // 获取个人信息
        qqinfo = getQQInfo(qqinfo);
        if (null == qqinfo)
        {
            return null;
        }
        
        // 更新凭证
        c.setCheckStatus(Credential.STATUS_APPROVED);
        credentialMapper.update(c);

        return qqinfo;
    }

    /**
     * 解析Authorization响应
     * @param request
     * @return
     */
    public Credential parseAuthorizationCode(HttpServletRequest request)
    {
        /*
         * 用户授权成功会携带code和state
         * 用户授权取消会usercancel和state,其中usercancel值非零
         * 调用接口失败时会返回code和msg字段
         */
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        String usercancel = request.getParameter("usercancel");
        String msg = request.getParameter("msg");

        logger.debug("qq oauth bakc params: ");
        logger.debug("state     : {}", state);
        logger.debug("code      : {}", code);
        logger.debug("usercancel: {}", usercancel);
        logger.debug("msg       : {}", msg);

        // 校验参数
        if (StringUtils.isNotEmpty(usercancel) && !"0".equals(usercancel.trim()))
        {
            logger.error("user cancel error: {}", usercancel);
            return null;
        }
        if (StringUtils.isNotEmpty(msg))
        {
            logger.error("code error: [{}],[{}]", code, msg);
            return null;
        }

        // 根据state查询对应凭证
        Credential c = credentialMapper.queryByPK(state);
        if (null == c)
        {
            logger.error("check credential error: query null");
            return null;
        }
        if (Credential.STATUS_CREATE != c.getCheckStatus())
        {
            logger.error("check credential status error: {}", c.getCheckStatus());
            return null;
        }

        // 更新凭证
        c.setCheckStatus(Credential.STATUS_DOING);
        c.setOauthCode(code);
        credentialMapper.update(c);

        return c;
    }

    /**
     * 获取accessToken和openid
     * @param credential
     * @return
     */
    public QqInfo getTokenAndOpenId(String code_state)
    {
        // 获取Access Token
        Map<String, String> tokenResp = getAccessToken(code_state);
        String code = tokenResp.get("code");
        String msg = tokenResp.get("msg");
        String accessToken = tokenResp.get("access_token");
        String expireIn = tokenResp.get("expires_in");
        String refreshToken = tokenResp.get("refresh_token");

        // 判断是否成功获取access_token
        if (!StringUtils.isEmpty(code))
        {
            logger.error("parse token error : [{}],[{}]", code, msg);
            return null;
        }
        if (StringUtils.isEmpty(accessToken))
        {
            logger.error("get accesstoken error : token null or empty.");
            return null;
        }

        // 获取OPENID
        String openId = getOpenId(accessToken);
        if (StringUtils.isEmpty(openId))
        {
            logger.error("get openId error.");
            return null;
        }

        // 查询是否存在
        QqInfo qqinfo = qqInfoMapper.queryByOpenId(openId);
        if (null == qqinfo)
        {
            qqinfo = new QqInfo();
            qqinfo.setOpenId(openId);
            qqinfo.setToken(accessToken);
            qqinfo.setExpiresIn(expireIn);
            qqinfo.setRefreshToken(refreshToken);
            qqinfo.setUpdateDate(new Date());
            qqInfoMapper.save(qqinfo);
        }
        else
        {
            qqinfo.setOpenId(openId);
            qqinfo.setToken(accessToken);
            qqinfo.setExpiresIn(expireIn);
            qqinfo.setRefreshToken(refreshToken);
            qqinfo.setUpdateDate(new Date());
            qqInfoMapper.update(qqinfo);
        }

        return qqinfo;
    }

    /**
     * 获取个人信息
     * @param qqinfo
     */
    public QqInfo getQQInfo(QqInfo qqinfo)
    {
        // 获取个人信息
        String userResp = getUserInfo(qqinfo.getToken(), qqinfo.getOpenId());
        if (StringUtils.isEmpty(userResp))
        {
            logger.error("get user info error.");
            return null;
        }

        // 更新QQ信息
        try
        {
            JSONObject json = JSONObject.fromObject(userResp);
            int ret = json.getInt("ret");
            if (0 != ret)
            {
                logger.error("get user info failure : [{}],[{}]", ret, json.getInt("msg"));
                return null;
            }

            String nickname = json.getString("nickname");
            String figureurl = json.getString("figureurl");
            String figureurl_1 = json.getString("figureurl_1");
            String figureurl_2 = json.getString("figureurl_2");
            String figureurl_qq_1 = json.getString("figureurl_qq_1");
            String figureurl_qq_2 = json.getString("figureurl_qq_2");
            String gender = json.getString("gender");
            String province = null;
            String city = null;
            try
            {
                province = json.getString("province");
                city = json.getString("city");
            }
            catch (Exception e)
            {
                logger.error("get city and province error.");
            }

            qqinfo.setNickName(nickname);
            qqinfo.setFigureUrl(figureurl);
            qqinfo.setFigureUrl1(figureurl_1);
            qqinfo.setFigureUrl2(figureurl_2);
            qqinfo.setFigureQqUrl(figureurl_qq_1);
            qqinfo.setFigureQqUrl2(figureurl_qq_2);
            qqinfo.setGender(gender);
            qqinfo.setProvince(province);
            qqinfo.setCity(city);
            qqInfoMapper.update(qqinfo);
        }
        catch (Exception e)
        {
            logger.error("parse token error.");
            return null;
        }

        return qqinfo;
    }

    /**
     * 获取accessToken
     * @param code
     * @return
     */
    public Map<String, String> getAccessToken(String code)
    {
        String uri = QqLoginUtils.getAccessTokenURL(code);
        return QqLoginUtils.paresUrlResponse(HttpUtils.get(uri));
    }

    /**
     * 获取OPENID
     * @param token
     * @return
     */
    public String getOpenId(String token)
    {
        // 发送请求
        String uri = QqLoginUtils.getOpenIdURL(token);
        String resp = HttpUtils.get(uri);

        String openid = null;
        // 解析
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(resp);
        if (m.find())
        {
            openid = m.group(1);
        }
        return openid;
    }

    /**
     * 创建QQ登录获取用户信息
     * @param code
     * @return
     */
    public String getUserInfo(String token, String openId)
    {
        String param = QqLoginUtils.getUserInfoURL(token, openId);
        return HttpUtils.get(param);
    }

}
