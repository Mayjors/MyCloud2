package com.example.consumerserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Slf4j
@Component
public class CuratorClientUtil implements DisposableBean {

    @Value("${zookeeper.connect}")
    private String zkConnect;

    private CuratorFramework client;

    public CuratorClientUtil(String zkConnect) {
        this.zkConnect = zkConnect;
    }

    public CuratorClientUtil() {
    }

    @PostConstruct
    public void init() {
        // 重试策略， 等待1s，最大重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder().connectString(zkConnect)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        this.client.start();
    }

    @Override
    public void destroy() throws Exception {
        try {
            if (Objects.nonNull(getClient())) {
                getClient().close();
            }
        } catch (Exception e) {
            log.info("CuratorFramework close error=>{}", e.getMessage());
        }
    }

    public CuratorFramework getClient() {
       return this.client;
    }
}
