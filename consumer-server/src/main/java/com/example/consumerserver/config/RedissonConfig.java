package com.example.consumerserver.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String redisConnectIp;

    @Value("${spring.redis.port}")
    private String redisConnectPort;

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+redisConnectIp+":"+redisConnectPort);
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
