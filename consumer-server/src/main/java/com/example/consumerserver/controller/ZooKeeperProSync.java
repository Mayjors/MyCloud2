package com.example.consumerserver.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.FileInputStream;
import java.nio.channels.Channels;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperProSync implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        String path = "/user";
        // 连接zk并且注册一个默认的监听器
        zk = new ZooKeeper("127.0.0.1:2181", 5000, new ZooKeeperProSync());
        // 等待zk连接成功
        FileInputStream fis = new FileInputStream("");
        fis.getChannel().transferTo(0, fis.getChannel().size(), Channels.newChannel(System.out));
        connectedSemaphore.await();
        // 获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zk.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // zk连接成功通知事件
        if (Event.KeeperState.SyncConnected ==watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                connectedSemaphore.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                // zk目录节点数据变化通知事件
//                try {
//                    System.out.println("配置已修改，新值为" + new String(zk.getData(watchedEvent.getPath(), true, stat)));
//                } catch (Exception e) {
//
//                }
            }
        }
    }
}
