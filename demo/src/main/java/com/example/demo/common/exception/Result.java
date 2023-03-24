package com.example.demo.common.exception;

import lombok.Data;

@Data
public class Result {

    private Integer code;
    private String message;
    private Object data;
}
