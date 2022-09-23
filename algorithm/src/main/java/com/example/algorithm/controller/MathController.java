package com.example.algorithm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("learn")
public class MathController {

    @RequestMapping("/math")
    public String learnMath() {
        return "xxx";
    }
}
