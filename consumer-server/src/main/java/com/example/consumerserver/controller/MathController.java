package com.example.consumerserver.controller;

import com.example.consumerserver.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("learn")
public class MathController {

    @Autowired
    private LearnService service;

    @RequestMapping("/math")
    public String learnMath() {
        String xx = service.getMath();
        System.out.println(xx);
        return "xxx";
    }
}
