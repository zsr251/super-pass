package com.javasoso.pass.config;

import com.javasoso.pass.util.RateLimitUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author apple
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * 流量限制器
     * @param redisTemplate
     * @return
     */
    @Bean("applyRateLimitUtil")
    public RateLimitUtil applyRateLimitUtil(RedisTemplate redisTemplate){
        return new RateLimitUtil(redisTemplate,"login",10, 1);
    }
}
