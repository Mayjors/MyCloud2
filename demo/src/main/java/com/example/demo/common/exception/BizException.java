package com.example.demo.common.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException{

    private Integer code;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Result result) {
        super(result.getMessage());
        this.code = result.getCode();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BizException(Result result, Throwable cause) {
        super(result.getMessage(), cause);
        this.code = result.getCode();
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}