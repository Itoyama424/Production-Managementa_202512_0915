package com.production.management.dto.requirements_planning;

import lombok.Data;

@Data
public class RequirementsPlanningViewDto {

	  // 基本情報
	  private String itemId;
	  private String itemName;
	  private String unit;

	  // --- 構成数(親1個当り) ---
	  //private String quantityDisp; 
	  // --- 総必要数 ---
	  private String totalRequiredDisp;
	  //--- 有効在庫 ---
	  private String availableQtyDisp;	  
	  // --- 在庫（Stock） ---
	  private String stockQtyDisp;
	  //--- 不良（Defective） ---
	  private String defectiveQtyDisp; 
	  //--- 保留（Hold） ---
	  private String holdQtyDisp;
	  // --- 不足数 ---
	  private String shortageQtyDisp;

}
