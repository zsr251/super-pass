package com.javasoso.pass.aop;

/**
 * 并发定时任务异常
 *
 * @author jasonzhu
 * @date 2018/4/19
 */
public class JobLimitException extends RuntimeException{

    public JobLimitException() {
    }

    public JobLimitException(String message) {
        super(message);
    }

    public JobLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobLimitException(Throwable cause) {
        super(cause);
    }
}
