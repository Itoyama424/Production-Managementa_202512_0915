package com.production.management.mapper.entity;

import org.apache.ibatis.annotations.Mapper;

import com.production.management.entity.ManufactureOrderEntity;

/**
 * 製造指示のMapperインターフェース
 */
@Mapper
public interface ManufactureOrderEntityMapper {
    
    // 製造指示をDBに登録する
    void insertManufactureOrder(ManufactureOrderEntity manufactureOrderEntity);
    
    // 製造指示番号のシーケンスを取得する
    Long getNextManufactureOrderSeq();
}
