package com.ifenwang.sco.common.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP服务类
 */
public class HttpUtils
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 发送post请求
     * @param uri
     * @param params
     * @return
     */
    public static String post(String uri, String[] params)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try
        {
            HttpPost httpPost = new HttpPost(uri);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (int i = 0; i < params.length / 2; i++)
            {
                nvps.add(new BasicNameValuePair(params[i * 2], params[i * 2 + 1]));
            }

            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            httpPost.setEntity(uefEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            logger.debug("http get response status: {}", response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String resp = EntityUtils.toString(entity, Charset.defaultCharset());
                logger.debug("http post response : {}", resp);
                EntityUtils.consume(entity);
                return resp;
            }
        }
        catch (Exception e)
        {
            logger.error("set http post request error: {}", e.getMessage());
        }
        finally
        {
            if (null != httpClient)
            {
                try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    logger.error("close http client error: {}", e.getMessage());
                }
            }
        }

        return null;
    }

    /**
     * 发送post请求
     * @param uri
     * @param params
     * @return
     */
    public static String post(String uri, String param)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try
        {
            StringEntity se = new StringEntity(param);
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(se);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            logger.debug("http get response status: {}", response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String resp = EntityUtils.toString(entity, Charset.defaultCharset());
                logger.debug("http post response : {}", resp);
                EntityUtils.consume(entity);
                return resp;
            }
        }
        catch (Exception e)
        {
            logger.error("set http post request error: {}", e.getMessage());
        }
        finally
        {
            if (null != httpClient)
            {
                try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    logger.error("close http client error: {}", e.getMessage());
                }
            }
        }

        return null;
    }

    /**
     * 发送get请求
     * @param uri
     * @return
     */
    public static String get(String uri)
    {
        logger.debug("http get uri : {}", uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try
        {
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            logger.debug("http get response status: {}", response.getStatusLine());
            //获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String resp = EntityUtils.toString(entity, Charset.defaultCharset());
                logger.debug("http get response : {}", resp);
                EntityUtils.consume(entity);
                return resp;
            }
        }
        catch (ClientProtocolException e)
        {
            logger.error("set http get request error: {}", e.getMessage());
        }
        catch (IOException e)
        {
            logger.error("set http get request error: {}", e.getMessage());
        }
        finally
        {
            if (null != httpClient)
            {
                try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    logger.error("close http client error: {}", e.getMessage());
                }
            }
        }

        return null;
    }

    public static void main(String[] args)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try
        {
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println("http get response status:" + response.getStatusLine());
            //获取响应实体
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String resp = EntityUtils.toString(entity, Charset.defaultCharset());
                System.out.println("http get response :");
                System.out.println(resp);
                EntityUtils.consume(entity);
            }
        }
        catch (ClientProtocolException e)
        {
            System.out.println(e);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if (null != httpClient)
            {
                try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    System.out.println(e);
                }
            }
        }
    }
}
