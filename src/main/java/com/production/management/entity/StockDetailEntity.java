package com.production.management.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StockDetailEntity {
    private String itemId;
    private BigDecimal stockQty;
    private BigDecimal defectQty;
    private BigDecimal holdQty;
    private BigDecimal allocatedQty;
    private LocalDateTime updatedAt;
}
