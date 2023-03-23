package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;//id

    private String operationUser;//操作人

    private String path;//请求路径

    private String time;//方法执行时间

    private String parameter;//方法入参

    private String title;//操作方法

    private String action;//方法描述

    private Integer sysType;//系统类型

    private Integer opType;//操作类型

    public SysLog(String operationUser, String path, String time,
                  String parameter, String title, String action, Integer sysType, Integer opType) {
        super();
        this.operationUser = operationUser;
        this.path = path;
        this.time = time;
        this.parameter = parameter;
        this.title = title;
        this.action = action;
        this.sysType = sysType;
        this.opType = opType;
    }

}
