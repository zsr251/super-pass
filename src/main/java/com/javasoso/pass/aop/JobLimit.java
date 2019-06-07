package com.javasoso.pass.aop;

import java.lang.annotation.*;

/**
 * 定时任务 同时触发限制
 *
 * @author jasonzhu
 * @date 2018/4/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JobLimit {

    /**
     * redis key
     */
    String key();

    /**
     * 定时任务名称
     * @return
     */
    String name();

    /**
     * key 超时时间 防止异常 未删除key
     * @return
     */
    int expireSecond() default 3600;

}
