package com.example.algorithm.pattern.observer;

public class Client {
    public static void main(String[] args) {
        SubscriptionSubject subscriptionSubject = new SubscriptionSubject();
        // 创建微信用户
        WeixinUser user1 = new WeixinUser("xxx01");
        WeixinUser user2 = new WeixinUser("xxx02");
        WeixinUser user3 = new WeixinUser("xxx03");

        // 订阅公众号
        subscriptionSubject.registerObserver(user1);
        subscriptionSubject.registerObserver(user2);
        subscriptionSubject.registerObserver(user3);

        subscriptionSubject.notify("xxxxx专栏更新了");
    }
}
