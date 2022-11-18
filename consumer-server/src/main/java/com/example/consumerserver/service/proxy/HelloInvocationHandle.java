package com.example.consumerserver.service.proxy;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class HelloInvocationHandle implements InvocationHandler {
    private Object object;
    public HelloInvocationHandle(Object o) {
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("method: " + method.getName() + "is invoked");
        System.out.println("proxy: " + proxy.getClass().getName());
        Object result = method.invoke(object, args);
        System.out.println("服务所需时间：" +(System.currentTimeMillis()-startTime));
        return result;
    }

    public static void main(String[] args) {
        UserService userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class[]{UserService.class}, new HelloInvocationHandle(new UserServiceImpl()));
        userService.request();
    }
}
