package com.production.management.mapper.manufacturing_order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.ItemMasterEntity;

@Mapper
public interface ManufacturingOrderMapper {

    // 調達区分を指定して部品マスタのリストを取得
    List<ItemMasterEntity> getItemMasterListByProcurementTypeM(@Param("procurementType") String procurementType);
}
