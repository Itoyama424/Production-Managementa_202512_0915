package com.production.management.mapper.entity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.StockDetailEntity;

@Mapper
public interface StockDetailMapper {
	StockDetailEntity getStockDetailByItemId(@Param("itemId") String itemId);
}
