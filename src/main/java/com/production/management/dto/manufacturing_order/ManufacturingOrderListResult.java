package com.production.management.dto.manufacturing_order;

import java.util.List;

import lombok.Data;

/**
 * 製造指示一覧結果DTO
 */
@Data
public class ManufacturingOrderListResult {
    
    // 製造指示一覧
    private List<ManufacturingOrderListViewDto> manufactureOrderList;
    
    // 総件数
    private int totalCount;
    
}
