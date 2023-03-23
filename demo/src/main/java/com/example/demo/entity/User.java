package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String passWord;
    private String realName;

}
