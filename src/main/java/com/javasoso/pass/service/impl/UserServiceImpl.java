package com.javasoso.pass.service.impl;

import com.javasoso.pass.constant.RedisKey;
import com.javasoso.pass.exception.SuperPassException;
import com.javasoso.pass.mapper.UserMapper;
import com.javasoso.pass.model.User;
import com.javasoso.pass.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByUserId(Integer userId) {
        if (userId == null){return null;}
        String userKey = RedisKey.USER_ + userId;
        try {
            User user = (User) redisTemplate.opsForValue().get(userKey);
            if(user != null){return user;}
        }catch (Exception e){
            log.warn("获取缓存用户失败 {}",e.getMessage());
        }

        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null){
            try {
                redisTemplate.opsForValue().set(userKey,user,300, TimeUnit.SECONDS);
            }catch (Exception e){
                log.warn("缓存用户失败 {}",e.getMessage());
            }
        }
        return user;
    }

    @Override
    public User register(String userName, String superPass) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(superPass)){
            throw new SuperPassException("参数异常");
        }
        User record = new User();
        record.setUserName(userName);
        List<User> userList = userMapper.select(record);
        if (userList != null && userList.size() > 0) {
            throw new SuperPassException("用户名重复");
        }
        record.setSuperPass(superPass);
        record.setStatus(1);
        userMapper.insertSelective(record);
        return record;
    }
}
