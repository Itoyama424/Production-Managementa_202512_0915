package com.production.management.dto.requirements_planning;

import java.math.BigDecimal;

public class RequirementsPlanningViewDto {

	  // 基本情報
	  private int level;
	  private String itemId;
	  private String itemName;
	  private String unit;

	  // --- 構成数(親1個当り) ---
	  private BigDecimal calcQuantity; 
	  private String quantityDisp; 
	  // --- 総必要数 ---
	  private BigDecimal calcTotalRequired;
	  private String totalRequiredDisp;
	  //--- 有効在庫 ---
	  private BigDecimal calcAvailableQty; 
	  private String availableQtyDisp;	  
	  // --- 在庫（Stock） ---
	  private BigDecimal calcStockQty;
	  private String stockQtyDisp;
	  //--- 不良（Defective） ---
	  private BigDecimal calcDefectiveQty;
	  private String defectiveQtyDisp; 
	  //--- 保留（Hold） ---
	  private BigDecimal calcHoldQty;
	  private String holdQtyDisp;
  
	 
	  // --- 不足数 ---
	  private String shortageQtyDisp;
	  private BigDecimal calcShortageQty;
}
