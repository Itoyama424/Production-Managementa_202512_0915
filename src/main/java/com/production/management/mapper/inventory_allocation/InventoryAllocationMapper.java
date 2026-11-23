package com.production.management.mapper.inventory_allocation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.ManufactureOrderEntity;
/**
 * 在庫引当のMapperインターフェース
 */
@Mapper
public interface InventoryAllocationMapper {
    /**
     * 有効な製造指示のリストを取得する
     * @return
     */
    List<ManufactureOrderEntity> findValidMoNoList(@Param("manufacturingOrderStatus") String manufacturingOrderStatus);
}
