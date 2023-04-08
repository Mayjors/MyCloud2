package com.example.user;

import com.example.user.service.AService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserApplication.class);
        AService aService = applicationContext.getBean("AService", AService.class);
        aService.test();
    }
}
