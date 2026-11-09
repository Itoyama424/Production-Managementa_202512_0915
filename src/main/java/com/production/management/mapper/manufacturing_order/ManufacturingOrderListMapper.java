package com.production.management.mapper.manufacturing_order;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.production.management.constant.ManufacturingOrderStatus;
import com.production.management.dto.manufacturing_order.ManufacturingOrderListViewDto;

@Mapper
public interface ManufacturingOrderListMapper {

    List<ManufacturingOrderListViewDto> selectManufactureOrderList(@Param("moNo") String moNo,
                                                                 @Param("itemId") String itemId, 
                                                                 @Param("startDateFrom") LocalDate startDateFrom,
                                                                 @Param("startDateTo") LocalDate startDateTo, 
                                                                 @Param("status") ManufacturingOrderStatus status);

}
