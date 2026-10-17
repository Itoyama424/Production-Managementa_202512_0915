package com.production.management.mapper.entity;

import org.apache.ibatis.annotations.Mapper;

import com.production.management.entity.ItemMasterEntity;

@Mapper
public interface ItemMasterMapper {
	
	ItemMasterEntity getItemMasterByItemId(String itemId);

}
