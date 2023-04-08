package com.example.consumerserver.controller;

import com.example.consumerserver.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class RedisLockController {
    @Autowired
    private RedisLockService redisLockService;

    @PostMapping("/seckill/{id}/{user}")
    public boolean secKill(@PathVariable Long id, @PathVariable String user){
        return redisLockService.updateStock(id,user);
    }
}
