package com.production.management.mapper.entity;


import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.BomMasterEntity;

@Mapper
public interface BomMasterMapper {
  
  List<BomMasterEntity> getRawByParentIdBomList(@Param("parentId") String parentId, @Param("baseDate") LocalDate baseDate);
  
  List<BomMasterEntity> getRawByEffectiveBomList(@Param("baseDate") LocalDate baseDate);

}
