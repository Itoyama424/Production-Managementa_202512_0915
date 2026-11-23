package com.production.management.form.inventory_allocation;

import java.time.LocalDate;

import lombok.Data;

/**
 * 在庫引当フォームクラス
 */
@Data
public class ManufactureOrderSearchForm {
    
    private String moNo;
    private String itemId;
    private String itemName;
    private LocalDate orderQty;
    private LocalDate startDate;
    private String dueDate;
    private String status;    

}
