package com.javasoso.pass.service;

import com.javasoso.pass.constant.PlatformEnum;
import com.javasoso.pass.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
public interface LoginService {
    /**
     * 判断token是否存在 返回用户信息
     *
     * @param token
     * @param platform 终端
     * @return
     */
    User checkToken(String token, PlatformEnum platform);

    /**
     * 简单登录 根据用户名 密码
     *
     * @param userName  用户名
     * @param superPass 密码
     * @param platform  终端
     * @param request
     * @return token
     */
    String loginByPassword(String userName, String superPass, PlatformEnum platform, HttpServletRequest request);

    /**
     * 登出
     *
     * @param userId
     * @param platform 终端
     * @return
     */
    boolean logout(Integer userId, PlatformEnum platform);
}
