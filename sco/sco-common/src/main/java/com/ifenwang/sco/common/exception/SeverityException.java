package com.ifenwang.sco.common.exception;

/**
 * 表示一个严重的不可恢复的错误. 该错误应当不会发生.
 * 如"sometext".getBytes("ASCII")所抛出的异常.
 */
public class SeverityException extends RuntimeException
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -506627259160307832L;

    /**
     * 构造方法.
     */
    public SeverityException()
    {
        super();
    }

    /**
     * 构造方法.
     * @param message 异常消息
     * @param cause   父异常
     */
    public SeverityException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 构造方法.
     * @param message 异常消息
     */
    public SeverityException(String message)
    {
        super(message);
    }

    /**
     * 构造方法.
     * @param cause 父异常
     */
    public SeverityException(Throwable cause)
    {
        super(cause);
    }

}
