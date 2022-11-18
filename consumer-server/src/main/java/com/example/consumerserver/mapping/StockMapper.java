package com.example.consumerserver.mapping;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.consumerserver.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
