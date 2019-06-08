package com.javasoso.pass.aop;

import java.lang.annotation.*;

/**
 * 校验登录状态
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckLogin {
}
