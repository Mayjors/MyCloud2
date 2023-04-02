package com.example.algorithm.pattern.factory.abstracts;

public class LowEngine implements Engine {
    @Override
    public void run() {
        System.out.println("转得慢！");
    }

    @Override
    public void start() {
        System.out.println("启动慢！");
    }
}
