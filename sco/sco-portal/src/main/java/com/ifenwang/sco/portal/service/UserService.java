package com.ifenwang.sco.portal.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifenwang.sco.common.exception.SeverityException;
import com.ifenwang.sco.common.storage.service.Configuration;
import com.ifenwang.sco.common.storage.entity.QqInfo;
import com.ifenwang.sco.common.storage.entity.UserInfo;
import com.ifenwang.sco.common.storage.entity.UserLoginLog;
import com.ifenwang.sco.common.storage.entity.UserPasswordLog;
import com.ifenwang.sco.common.storage.mapper.CredentialMapper;
import com.ifenwang.sco.common.storage.mapper.UserInfoMapper;
import com.ifenwang.sco.common.storage.mapper.UserLoginLogMapper;
import com.ifenwang.sco.common.storage.mapper.UserPasswordLogMapper;
import com.ifenwang.sco.common.utils.HmacMd5Utils;
import com.ifenwang.sco.common.utils.IPUtils;
import com.ifenwang.sco.common.utils.IdGenerator;
import com.ifenwang.sco.portal.common.CommonResult;
import com.ifenwang.sco.portal.common.CommonResultStatus;
import com.ifenwang.sco.portal.common.constants.SystemConstants;
import com.ifenwang.sco.portal.utils.LogUtils;
import com.ifenwang.sco.portal.utils.LoginUtils;

/**
 * 用户服务类
 */
@Service("userService")
public class UserService
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

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
     * userPasswordLogMapper
     */
    @Resource
    private UserPasswordLogMapper userPasswordLogMapper;

    /**
     * credentialMapper
     */
    @Resource
    private CredentialMapper credentialMapper;

    /**
     * 根据QQ信息更新用户信息
     * @param openId
     * @return
     */
    public UserInfo updateUserInfoByQQ(String openId)
    {
        // 查询对应的用户是否存在
        UserInfo userInfo = userInfoMapper.queryByQQ(openId);
        if (null == userInfo)
        {
            // 创建新user
            userInfo = new UserInfo();
            userInfo.setUserId(IdGenerator.createUserId());
            userInfo.setUserQQ(openId);
            userInfo.setRegistDate(new Date());
            userInfo.setLoginDate(new Date());
            userInfoMapper.save(userInfo);
        }

        return userInfo;
    }

    /**
     * 登录操作
     * @param request 请求
     * @param userName 用户名
     * @param password 密码
     * @param credential 凭证
     * @param loginType 登录类型
     * @return
     */
    public CommonResult login(HttpServletRequest request, String userName, String password, String loginType)
    {
        UserInfo userInfo = null;
        UserPasswordLog passwordLog = null;

        // 判断登录类型
        if (SystemConstants.LOGINTYPE_PC.equals(loginType))
        {
            // 校验参数
            if (StringUtils.isEmpty(userName))
            {
                LogUtils.baseErrorLog(logger, "", "user Name is null or empty");
                return new CommonResult(CommonResultStatus.INVALID_USERNAME_PASSWORD);
            }
            if (StringUtils.isEmpty(password))
            {
                LogUtils.baseErrorLog(logger, "", "password is null or empty");
                return new CommonResult(CommonResultStatus.INVALID_USERNAME_PASSWORD);
            }

            userInfo = userInfoMapper.queryByUserName(userName);
            if (null == userInfo)
            {
                LogUtils.baseErrorLog(logger, "", "user does not exist");
                return new CommonResult(CommonResultStatus.INVALID_USERNAME_PASSWORD);
            }

            // 校验是否锁定
            passwordLog = userPasswordLogMapper.queryByUserId(userInfo.getUserId());
            if (null != passwordLog && Configuration.User.maxPasswordRetry <= passwordLog.getLoginCount())
            {
                int expire = Configuration.User.passwordLockTime * 60 * 1000;
                if (System.currentTimeMillis() - passwordLog.getLoginTime().getTime() < expire)
                {
                    LogUtils.baseErrorLog(logger, userInfo.getUserId(), "user is locked for password attemption");
                    return new CommonResult(CommonResultStatus.INVALID_USER_LOCKED);
                }
            }

            // 校验密码
            String passwordHash = HmacMd5Utils.hashToString(password, userInfo.getUserId());
            if (!passwordHash.equals(userInfo.getPasswordHash()))
            {
                LogUtils.baseErrorLog(logger, userInfo.getUserId(), "login falid, invalid password");

                // 更新或新建密码日志
                if (null == passwordLog)
                {
                    passwordLog = new UserPasswordLog();
                    passwordLog.setUserId(userInfo.getUserId());
                    passwordLog.setLoginCount(1);
                    passwordLog.setLoginTime(new Date());
                    userPasswordLogMapper.save(passwordLog);
                }
                else
                {
                    UserPasswordLog update = new UserPasswordLog();
                    update.setUserId(userInfo.getUserId());
                    update.setLoginCount(passwordLog.getLoginCount() + 1);
                    update.setLoginTime(new Date());
                    userPasswordLogMapper.update(update);
                }
                return new CommonResult(CommonResultStatus.INVALID_USERNAME_PASSWORD);
            }

            // TODO 判断密码是否过期

            // 清除密码错误记录
            if (null != passwordLog)
            {
                userPasswordLogMapper.deleteByUserId(userInfo.getUserId());
            }
        }
        else if (SystemConstants.LOGINTYPE_QQ.equals(loginType))
        {
            // 校验QQ是否已经授权
            HttpSession session = request.getSession();
            QqInfo qqinfo = (QqInfo) session.getAttribute(SystemConstants.SESSION_USER_QQ);
            if (null == qqinfo)
            {
                LogUtils.baseErrorLog(logger, "", "qqinfo in session deos not exist");
                return new CommonResult(CommonResultStatus.INVALID_QQ);
            }

            userInfo = updateUserInfoByQQ(qqinfo.getOpenId());
        }
        else
        {
            throw new SeverityException("bad login type");
        }

        // 登陆成功，保存登陆日志
        saveLoginLog(request, userInfo.getUserId(), userName, loginType);

        // 保存到session
        HttpSession session = request.getSession();
        session.setAttribute(SystemConstants.SESSION_USER_PC, userInfo);

        LogUtils.baseLog(logger, userInfo.getUserId(), "login success");

        CommonResult result = new CommonResult(CommonResultStatus.SUCCESS);
        result.setRedirectUri(LoginUtils.getRedirectUrl(request));
        return result;
    }

    /**
     * 保存登陆日志
     * @param request 请求
     * @param userId 操作员ID
     * @param userName 用户名
     * @param loginType 登录类型
     */
    private void saveLoginLog(HttpServletRequest request, String userId, String userName, String loginType)
    {
        // 保存登录信息
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUserId(userId);
        loginLog.setLoginTime(new Date());
        loginLog.setIpAddress(IPUtils.getRemoteAddress(request));
        loginLog.setUserAgent(request.getHeader("User-Agent"));
        loginLog.setLoginType(loginType);
        userLoginLogMapper.save(loginLog);
    }
}
