package com.example.demo.config;

import com.example.demo.common.exception.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Order(1)
@RestControllerAdvice
public class GlobalExceptionHandler {
   private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

   public GlobalExceptionHandler() {
   }

   @ExceptionHandler({})
   public Result paramsExceptionHandler(HttpServletRequest request, Exception e) {
      return null;
   }

}
