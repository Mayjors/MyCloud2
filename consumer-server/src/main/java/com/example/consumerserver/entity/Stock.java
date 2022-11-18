package com.example.consumerserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_test")
public class Stock {
    @TableId
    private Long id;
    private Integer stock;

}