package com.example.consumerserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LearnService3 {

    @Autowired
    private RestTemplate restTemplate;

    public String getMath() {
        return restTemplate.getForObject("http://provider-server/learn/math", String.class);
    }

}