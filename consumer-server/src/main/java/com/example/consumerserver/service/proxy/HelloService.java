package com.example.consumerserver.service.proxy;

public class HelloService {
    public HelloService() {
        System.out.println("HellloService构造");
    }

    final public String sayOthers(String name) {
        System.out.println("HelloService:sayOthers>>" + name);
        return null;
    }

    public void sayHello() {
        System.out.println("HelloService:sayHello");
    }
}
