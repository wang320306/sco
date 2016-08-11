package com.ifenwang.sco.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.ifenwang.sco.common.exception.SeverityException;

/**
 * HMACMD5算法工具
 */
public class HmacMd5Utils
{
    /**
     * 计算HAMCMD5.
     * @param data Hash数据
     * @param key 密钥
     * @return HMACMD5
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] hash(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec sk = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(sk);
        return mac.doFinal(data);
    }

    /**
     * 计算HAMCMD5并转换为字符串.
     * @param data Hash数据
     * @param key 密钥
     * @return HMACMD5字符串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String hashToString(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException
    {
        return ByteUtils.toHexString(hash(data, key));
    }

    /**
     * 计算HAMCMD5并转换为字符串
     * @param data Hash数据
     * @param key 密钥
     * @return HMACMD5字符串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String hashToString(String data, String key)
    {
        try
        {
            byte[] dataByte = data.getBytes("ASCII");
            byte[] keyByte = key.getBytes("ASCII");
            return hashToString(dataByte, keyByte);
        }
        catch (InvalidKeyException e)
        {
            throw new SeverityException("HmacMd5Utils error : " + e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            throw new SeverityException("HmacMd5Utils error : " + e.getMessage());
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new SeverityException("HmacMd5Utils error : " + e.getMessage());
        }
    }

}
