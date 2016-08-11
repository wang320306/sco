package com.ifenwang.sco.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifenwang.sco.common.exception.SeverityException;
import com.ifenwang.sco.common.storage.service.Configuration;

/**
 * QQ登录工具
 */
public class QqLoginUtils
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(QqLoginUtils.class);

    /**
     * 认证回调地址
     */
    public static final String OAUTHBAKCURI = Configuration.Global.portalUrl + "/qqOAuth.php";

    /**
     * 获取Authorization Code的请求URL地址
     */
    public static final String AUTHORIZATIONCODE_URL = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 获取AccessToken的请求URL地址
     */
    public static final String ACCESSTOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    /**
     * 获取OpenId的请求URL地址
     */
    public static final String OPENID_URL = "https://graph.qq.com/oauth2.0/me";

    /**
     * 获取用户基本信息的请求URL地址
     */
    public static final String GETUSERINFO_URL = "https://graph.qq.com/user/get_user_info";

    //------------------------------------------------------------------------------------------------------

    /**
     * 获取Authorization Code的GET请求参数
     */
    public static final String AUTHORIZATIONCODE_PARAMS = "?response_type=RESPONSE_TYPE&client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&state=STATE";

    /**
     * 获取AccessToken的GET请求参数
     */
    public static final String ACCESSTOKEN_PARAMS = "?grant_type=GRANT_TYPE&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE&redirect_uri=REDIRECT";

    /**
     * 获取OPENID的URL地址
     */
    public static final String OPENID_PARAMS = "?access_token=ACCESS_TOKEN";

    /**
     * 获取USERINFO的URL地址
     */
    public static final String GETUSERINFO_PARAMS = "?access_token=ACCESS_TOKEN&oauth_consumer_key=CLIENT_ID&openid=OPENID";

    /**
     * 获取转换后的回调地址
     * @return
     */
    public static String getEncoderBackUrl()
    {
        String uri = null;
        try
        {
            uri = URLEncoder.encode(OAUTHBAKCURI, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("url encoder error.");
            throw new SeverityException("url encoder error.");
        }
        return uri;
    }

    /**
     * 获取Authorization Code的URL地址
     * @param state 凭证
     * @return url地址
     */
    public static String getAuthorizeURL(String state)
    {
        String uri = getEncoderBackUrl();
        String param = QqLoginUtils.AUTHORIZATIONCODE_PARAMS;
        param = param.replace("RESPONSE_TYPE", "code");
        param = param.replace("CLIENT_ID", Configuration.QqLogin.appId);
        param = param.replace("REDIRECT_URI", uri);
        param = param.replace("STATE", state);
        return QqLoginUtils.AUTHORIZATIONCODE_URL + param;
    }

    /**
     * 获取accessToken的URL地址
     * @param state 凭证
     * @return url地址
     */
    public static String getAccessTokenURL(String code)
    {
        String uri = getEncoderBackUrl();
        String param = QqLoginUtils.ACCESSTOKEN_PARAMS;
        param = param.replace("GRANT_TYPE", "authorization_code");
        param = param.replace("CLIENT_ID", Configuration.QqLogin.appId);
        param = param.replace("CLIENT_SECRET", Configuration.QqLogin.appKey);
        param = param.replace("CODE", code);
        param = param.replace("REDIRECT", uri);
        return QqLoginUtils.ACCESSTOKEN_URL + param;
    }

    /**
     * 获取openId的URL地址
     * @param token token
     * @return url地址
     */
    public static String getOpenIdURL(String token)
    {
        String param = QqLoginUtils.OPENID_PARAMS;
        param = param.replace("ACCESS_TOKEN", token);
        return QqLoginUtils.OPENID_URL + param;
    }

    /**
     * 获取userInfo的URL地址
     * @param token token
     * @param openId openId
     * @return url地址
     */
    public static String getUserInfoURL(String token, String openId)
    {
        String uri = QqLoginUtils.GETUSERINFO_PARAMS;
        uri = uri.replace("ACCESS_TOKEN", token);
        uri = uri.replace("CLIENT_ID", Configuration.QqLogin.appId);
        uri = uri.replace("OPENID", openId);
        return QqLoginUtils.GETUSERINFO_URL + uri;
    }

    /**
     * 
     * @param resp
     * @return
     */
    public static Map<String, String> paresUrlResponse(String resp)
    {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(resp))
        {
            return map;
        }

        String[] splits = resp.split("&");
        String[] params;
        for (String sp : splits)
        {
            params = sp.split("=");
            map.put(params[0], params[1]);
        }
        return map;
    }

}
