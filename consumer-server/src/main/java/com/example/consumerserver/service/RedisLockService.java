package com.example.consumerserver.service;

public interface RedisLockService {
    boolean updateStock(Long id,String user);
}
