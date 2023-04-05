package com.example.algorithm.pattern.singleton;

/**
 * 懒汉模式
 * 线程不安全
 * 多线程访问。第一个线程在if判断时，另外一个线程也判断直接new对象，导致两个线程都创建了对象
 * 所以可以加锁
 */
public class Singleton2 {
    private static volatile Singleton2 Instance;

    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (Instance == null) {
            synchronized (Singleton2.class) {
                if (Instance == null) {
                    Instance = new Singleton2();
                }
            }
        }
        return Instance;
    }

    public static void main(String[] args) {
        Singleton2 singleton = Singleton2.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton2 == singleton);
    }
}
