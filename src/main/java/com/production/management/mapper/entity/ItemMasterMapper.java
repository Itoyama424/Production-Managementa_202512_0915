package com.production.management.mapper.entity;

import org.apache.ibatis.annotations.Mapper;

import com.production.management.entity.ItemMasterEntity;

/**
 * 部品マスタのMapperインターフェース
 */
@Mapper
public interface ItemMasterMapper {
    
	// 部品マスタの取得
	ItemMasterEntity getItemMasterByItemId(String itemId);
	

}
