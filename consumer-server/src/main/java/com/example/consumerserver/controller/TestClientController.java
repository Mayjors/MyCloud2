package com.example.consumerserver.controller;

import com.example.consumerserver.utils.CuratorClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestClientController {
    /**
     * 卖10张票，要求全部卖出，不能超卖
     */
    private int tickets = 10;
    @Autowired
    private CuratorClientUtil curatorClientUtil;

    private String rootLock = "/rootLock";

    @GetMapping("/testLock")
    public Object testLock() throws Exception {
        String threadName = Thread.currentThread().getName();
//        InterProcessMutex mutex = new InterProcessMutex(null, "/lock");
        InterProcessMutex mutex = new InterProcessMutex(curatorClientUtil.getClient(), "/lock");
        try {
            // 尝试获取锁
            boolean lockFlag = mutex.acquire(3000, TimeUnit.SECONDS);
            // 获取锁成功
            if (lockFlag) {
                log.info("当前票数： {}", tickets);
                Thread.sleep(100);
                tickets --;
            } else {
                log.info("{}---获取锁fail", threadName);
            }
        } catch (Exception e) {
            log.info("{}---获取锁异常", threadName);
        } finally {
            //释放锁
            mutex.release();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data","线程: "+threadName+"执行完成");
        return jsonObject.toString();

    }
}
