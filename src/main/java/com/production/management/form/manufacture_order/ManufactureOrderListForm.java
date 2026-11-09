package com.production.management.form.manufacture_order;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.production.management.constant.ManufacturingOrderStatus;

import lombok.Data;

/**
 * 製造指示一覧画面のフォーム
 */
@Data
public class ManufactureOrderListForm {
    
    // 製造指示番号
    private String moNo; 
    // 部品ID
    private String itemId;
    // 開始日（From）
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDateFrom;
    // 開始日（To）
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDateTo;
    /** ステータス */
    private ManufacturingOrderStatus status;
}
