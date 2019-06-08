package com.javasoso.pass.aop;

import com.javasoso.pass.constant.PlatformEnum;
import com.javasoso.pass.constant.ResultModel;
import com.javasoso.pass.model.User;
import com.javasoso.pass.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录拦截
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Aspect
@Component
public class CheckLoginAspect {
    @Autowired
    private LoginService loginService;

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<PlatformEnum> platformThreadLocal = new ThreadLocal<>();

    @Pointcut("@annotation(com.javasoso.pass.aop.CheckLogin)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        // 接收的请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = getValue(request, "token");
        if (StringUtils.isEmpty(token)) {
            return new ResultModel<>(ResultModel.RESULT_AUTH_INVALID, "登录失效");
        }
        // 客户端类型
        PlatformEnum platform = PlatformEnum.WEB;
        String client = getValue(request, "client");
        if (!StringUtils.isEmpty(client)) {
            try {
                platform = PlatformEnum.getEnum(Integer.valueOf(client));
            } catch (Exception e) {
                log.warn("客户端类型获取异常：{}", e.getMessage());
            }
        }
        platform = platform == null ? PlatformEnum.WEB : platform;
        User user = loginService.checkToken(token, platform);
        if (user == null) {
            return new ResultModel<>(ResultModel.RESULT_AUTH_INVALID, "登录失效");
        }
        userThreadLocal.set(user);
        platformThreadLocal.set(platform);
        return joinPoint.proceed();
    }

    @AfterReturning("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        clearCurrentUser();
        clearCurrentPlatform();
    }

    /**
     * 内部方法 参数不判空
     *
     * @param request
     * @param name
     * @return
     */
    private String getValue(HttpServletRequest request, String name) {
        String v = request.getHeader(name);
        if (v != null) {
            return v;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getCurrentUser() {
        return userThreadLocal.get();
    }

    /**
     * 清除当前登录用户
     */
    public static void clearCurrentUser() {
        userThreadLocal.remove();
    }

    /**
     * 获取当前的终端
     * @return
     */
    public static PlatformEnum getCurrentPlatform() {
        return platformThreadLocal.get();
    }

    /**
     * 清除当前终端
     */
    public static void clearCurrentPlatform() {
        platformThreadLocal.remove();
    }
}
