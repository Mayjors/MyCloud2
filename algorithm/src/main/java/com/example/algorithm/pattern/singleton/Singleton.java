package com.example.algorithm.pattern.singleton;

/**
 * 饿汉模式
 * 类加载到内存后，就实力化一个单例，JVM保证线程安全
 * 唯一缺点：不管用到没，类装载时就完成实例化
 */
public class Singleton {
    private static Singleton Instance = new Singleton();

//    static {
//        Instance = new Singleton();
//    }

    private Singleton() {}

    public static Singleton getInstance() {
        return Instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton2 == singleton);
    }
}
