package com.example.algorithm.pattern.singleton;

/**
 * 枚举单例，不仅可以解决线程同步，还可以防止反序列化
 */
public enum Singleton4 {
    INSTANCE;

    public static void main(String[] args) {
        System.out.println(Singleton4.INSTANCE.hashCode());
    }
}
