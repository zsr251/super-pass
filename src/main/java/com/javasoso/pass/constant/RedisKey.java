package com.javasoso.pass.constant;

/**
 * 存放redis key
 */
public interface RedisKey {
    /**
     * 限制器
     */
    String LIMIT_ = "limit:";
    /**
     * token token 标识
     */
    String TOKEN_TOKEN_ = "token:token:";
    /**
     * token user 标识
     */
    String TOKEN_USER_ = "token:user:";
    /**
     * 用户信息
     */
    String USER_ = "user:";
}
