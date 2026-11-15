package com.production.management.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StockEntity {
    private String itemId;
    private BigDecimal stockQty;
    private BigDecimal defectiveQty;
    private BigDecimal holdQty;
    private BigDecimal allocatedQty;
//    private LocalDateTime updatedAt;
}
