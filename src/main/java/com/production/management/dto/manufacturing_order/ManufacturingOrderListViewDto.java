package com.production.management.dto.manufacturing_order;

import com.production.management.constant.ManufacturingOrderStatus;

import lombok.Data;

/**
 * 製造指示一覧画面のDTO
 */
@Data
public class ManufacturingOrderListViewDto {
    
    // 製造指示番号
    String moNo;
    // 部品ID
    String itemId;
    // 部品名
    String itemName;
    // 製造指示数量
    String orderQty;
    // 開始日
    String startDate;
    // 納期
    String dueDate;
    // ステータス
    ManufacturingOrderStatus status;
    // 備考
    String remarks;  
}
