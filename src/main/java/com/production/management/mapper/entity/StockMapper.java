package com.production.management.mapper.entity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.StockEntity;

@Mapper
public interface StockMapper {
	
	StockEntity getStockDetailByItemId(@Param("itemId") String itemId);
}
