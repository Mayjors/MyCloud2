package com.example.demo.service;

import com.example.demo.config.OperationAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {
    @Autowired
    private UserService userService;

    @Transactional
    @OperationAnnotation
    public void test() {
        System.out.println(this);
    }
}
