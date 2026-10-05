package com.production.management.mapper.lazyload;


import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.entity.BomMasterEntity;

@Mapper
public interface BomMasterMapper {
  
  List<BomMasterEntity> getRawBomList(@Param("baseDate") LocalDate baseDate);

}
