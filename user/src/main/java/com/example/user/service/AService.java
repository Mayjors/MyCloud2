package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AService {
    @Autowired
    private BService bService;

    public void test() {
        System.out.println(bService);
    }
}
