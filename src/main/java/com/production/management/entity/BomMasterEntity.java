package com.production.management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class BomMasterEntity {
	 
	private String parentId;       // 親品目ID
	private String itemId;         // 子品目ID (child_id)
	private BigDecimal quantity;   // 構成数
	private LocalDate effectiveDate; // 有効日
	private LocalDate expireDate;  // 失効日
	private Integer processId;     // 工程ID
	private String remarks;        // 備考
}