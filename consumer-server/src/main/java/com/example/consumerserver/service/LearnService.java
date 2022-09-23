package com.example.consumerserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "provider-server",path = "/learn")
public interface LearnService {

    @RequestMapping(value = "math")
    String getMath();

}