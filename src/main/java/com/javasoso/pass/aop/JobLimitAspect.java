package com.javasoso.pass.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务并发拦截处理
 *
 * @author jasonzhu
 * @date 2018/4/19
 */
@Slf4j
@Aspect
@Component
public class JobLimitAspect {
    @Autowired
    private RedisTemplate redisTemplate;
    @Pointcut("@annotation(com.javasoso.pass.aop.JobLimit)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        JobLimit jobLimit = method.getAnnotation(JobLimit.class);
        long n = redisTemplate.opsForValue().increment(jobLimit.key(), 1);
        if (n == 1){
            // 设置超时时间
            redisTemplate.expire(jobLimit.key(), jobLimit.expireSecond(), TimeUnit.SECONDS);
        }
        if (n > 1){
            log.error("正在进行：{} key：{} 本次为第：{}次",jobLimit.name(),jobLimit.key(),n);
            throw new JobLimitException("任务正在运行");
        }
        log.debug("任务：{} key：{} 开始执行 超时时间：{}秒",jobLimit.name(),jobLimit.key(),jobLimit.expireSecond());

    }

    @AfterReturning("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        JobLimit jobLimit = method.getAnnotation(JobLimit.class);
        redisTemplate.delete(jobLimit.key());
        log.debug("任务：{} key：{} 结束执行",jobLimit.name(),jobLimit.key());
    }

    @PostConstruct
    public void init(){
        log.info("-------定时任务并发限制器初始化成功-------");
    }

    @PreDestroy
    public void destory(){
        log.info("-------定时任务并发限制器销毁-------");
    }
}
