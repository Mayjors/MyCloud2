package com.example.algorithm.pattern.singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 */
public class Singleton3 {

    private Singleton3() {}

    private static class Holder {
        private final static Singleton3 Instance = new Singleton3();
    }
    public static Singleton3 getInstance() {
        return Holder.Instance;
    }

    public static void main(String[] args) {
        Singleton3 singleton = Singleton3.getInstance();
        Singleton3 singleton2 = Singleton3.getInstance();
        System.out.println(singleton2 == singleton);
    }
}
