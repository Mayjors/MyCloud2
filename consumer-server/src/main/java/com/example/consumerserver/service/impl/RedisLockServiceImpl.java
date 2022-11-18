package com.example.consumerserver.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.consumerserver.entity.Stock;
import com.example.consumerserver.entity.User;
import com.example.consumerserver.mapping.StockMapper;
import com.example.consumerserver.mapping.UserMapper;
import com.example.consumerserver.service.RedisLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockServiceImpl extends ServiceImpl<StockMapper, Stock> implements RedisLockService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStock(Long id, String name) {
        synchronized (name.intern()) {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>();
            userLambdaQueryWrapper.eq(User::getName, name);
            User count = userMapper.selectOne(userLambdaQueryWrapper);

            if (count !=null){
                return false;
            }

            //1、更新库存
            //等同于 update set stock = stock - 1 where id=#{id} and stock > 0
            this.update().setSql("stock = stock - 1")
                    .eq("id", id).gt("stock", 0)
                    .update();

            //2、模拟业务超时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }

            //3、插入订单
            User userOrder = new User();
            userOrder.setId(Long.valueOf(RandomUtil.randomNumbers(6)));
            userOrder.setName(name);

            userMapper.insert(userOrder);
        }
        return false;
    }

    public boolean updateStock2(Long id, String name) {
        // 使用Redission分布式锁
       RLock lock =  redissonClient.getLock("Redisson:lock:stock:" + id);
        try {
           // 尝试获取锁
            boolean isLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (isLock) {
                LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
                userLambdaQueryWrapper.eq(User::getName, name);
                User count = userMapper.selectOne(userLambdaQueryWrapper);

                if (count !=null){
                    return false;
                }

                //1、更新库存
                //等同于 update set stock = stock - 1 where id=#{id} and stock > 0
                this.update().setSql("stock = stock - 1")
                        .eq("id", id).gt("stock", 0)
                        .update();

                //2、插入订单
                User userOrder = new User();

                userOrder.setId(Long.valueOf(RandomUtil.randomNumbers(6)));
                userOrder.setName(name);
                userMapper.insert(userOrder);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            lock.unlock();
        }
        return true;
    }
}
