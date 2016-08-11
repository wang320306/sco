package com.ifenwang.sco.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公共拦截器
 */
public class CommonInterceptor implements HandlerInterceptor
{
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //获得请求路径的url
        String requestUri = request.getRequestURI();
        
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        
        logger.info("requestUri:"+requestUri);    
        logger.info("contextPath:"+contextPath);    
        logger.info("url:"+url);
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
        //logger.debug("enter Common interceptor postHandle method");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        //logger.debug("enter Common interceptor afterCompletion method");
    }

}
