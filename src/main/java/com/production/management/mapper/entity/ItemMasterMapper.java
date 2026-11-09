package com.production.management.mapper.entity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.ItemMasterEntity;

/**
 * 部品マスタのMapperインターフェース
 */
@Mapper
public interface ItemMasterMapper {
    
	// 部品マスタの取得
	ItemMasterEntity getItemMasterByItemId(String itemId);
	
	// 調達区分を指定して部品マスタのリストを取得
	List<ItemMasterEntity> getItemMasterListByProcurementTypeM(@Param("procurementType") String procurementType);

}
