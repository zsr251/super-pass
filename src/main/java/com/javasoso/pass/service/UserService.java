package com.javasoso.pass.service;

import com.javasoso.pass.model.User;

/**
 * 用户服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
public interface UserService {
    /**
     * 根据 用户ID 获取 User
     * @return
     */
    User getUserByUserId(Integer userId);

    /**
     * 注册
     * @param userName 用户名
     * @param superPass 密码
     * @return
     */
    User register(String userName,String superPass);
}
