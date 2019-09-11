package com.javasoso.pass.service.impl;

import com.google.common.collect.Lists;
import com.javasoso.pass.constant.LoginTypeEnum;
import com.javasoso.pass.constant.PlatformEnum;
import com.javasoso.pass.constant.RedisKey;
import com.javasoso.pass.exception.SuperPassException;
import com.javasoso.pass.mapper.LoginLogMapper;
import com.javasoso.pass.mapper.UserMapper;
import com.javasoso.pass.model.LoginLog;
import com.javasoso.pass.model.User;
import com.javasoso.pass.service.LoginService;
import com.javasoso.pass.service.UserService;
import com.javasoso.pass.util.BCrypt;
import com.javasoso.pass.util.ToolsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 登录服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkToken(String token, PlatformEnum platform) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        platform = platform == null ? PlatformEnum.WEB : platform;
        String tokenKey = RedisKey.TOKEN_TOKEN_ + token + "_" + platform.getCode();
        Integer userId = null;
        try {
            String userIdStr = (String) redisTemplate.opsForValue().get(tokenKey);
            userId = Integer.parseInt(userIdStr);
        } catch (Exception e) {
            log.warn("缓存中获取token失败 {}", e.getMessage());
        }
        if (userId != null) {
            return userService.getUserByUserId(userId);
        }
        LoginLog record = new LoginLog();
        record.setToken(token);
        record.setPlatform(platform.getCode());
        List<LoginLog> logs = loginLogMapper.select(record);
        if (logs == null || logs.size() < 1) {
            return null;
        }
        // 获取登录信息
        record = logs.get(logs.size() - 1);
        try {
            // 缓存 一小时
            redisTemplate.opsForValue().set(tokenKey, String.valueOf(record.getUserId()), 3600, TimeUnit.SECONDS);
            String tokenUserKey = RedisKey.TOKEN_USER_ + record.getUserId() + "_" + platform.getCode();
            // 缓存 根据用户 找到 token
            redisTemplate.opsForValue().set(tokenUserKey, token, 3666, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.warn("缓存token失败 {}", e.getMessage());
        }

        return userService.getUserByUserId(record.getUserId());
    }

    @Override
    public String loginByPassword(String userName, String superPass, PlatformEnum platform, HttpServletRequest request) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(superPass)) {
            throw new SuperPassException("登录参数错误");
        }
        platform = platform == null ? PlatformEnum.WEB : platform;
        User record = new User();
        record.setUserName(userName);
        List<User> userList = userMapper.select(record);
        // 未找到用户
        if (userList == null || userList.size() < 1) {
            throw new SuperPassException("用户名或密码错误");
        }
        record = userList.get(0);

        // 生成 loginLog
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(record.getId());
        loginLog.setPlatform(platform.getCode());
        loginLog.setLoginType(LoginTypeEnum.PASSWORD.getCode());
        loginLog.setAgent(ToolsUtil.getUserAgent(request));
        loginLog.setIp(ToolsUtil.getRemoteIp(request));
        loginLog.setStatus(0);

        // 校验密码
        if (!BCrypt.checkpw(record.getSuperPass(), superPass)) {
            loginLogMapper.insert(loginLog);
            throw new SuperPassException("用户名或密码错误");
        }
        // 把当前终端登录 踢下线
        logout(record.getId(), platform);
        // 生成token
        String token = UUID.randomUUID().toString();

        loginLog.setToken(token);
        loginLog.setStatus(1);
        loginLogMapper.insertSelective(loginLog);

        try {
            String tokenKey = RedisKey.TOKEN_TOKEN_ + token + "_" + platform.getCode();
            // 缓存 一小时
            redisTemplate.opsForValue().set(tokenKey, String.valueOf(record.getId()), 3600, TimeUnit.SECONDS);
            String tokenUserKey = RedisKey.TOKEN_USER_ + record.getId() + "_" + platform.getCode();
            // 缓存 根据用户 找到 token
            redisTemplate.opsForValue().set(tokenUserKey, token, 3666, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.warn("缓存token失败 {}", e.getMessage());
        }

        return token;
    }

    @Override
    public boolean logout(Integer userId, PlatformEnum platform) {
        if (userId == null) {
            return false;
        }
        platform = platform == null ? PlatformEnum.WEB : platform;
        // 把数据库数据置为失效
        loginLogMapper.clearToken(userId, platform.getCode());
        // 把缓存中登录信息清除
        try {
            String tokenUserKey = RedisKey.TOKEN_USER_ + userId + "_" + platform.getCode();
            String token = (String) redisTemplate.opsForValue().get(tokenUserKey);
            if (StringUtils.isEmpty(token)) {
                return true;
            }
            String tokenKey = RedisKey.TOKEN_TOKEN_ + token + "_" + platform.getCode();
            redisTemplate.delete(Lists.newArrayList(tokenKey, tokenUserKey));
        } catch (Exception e) {
            log.warn("清理缓存中的token失败 {}", e.getMessage());
        }
        return true;
    }


}
