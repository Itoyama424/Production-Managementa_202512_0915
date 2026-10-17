package com.production.management.dto.lazyload;

import lombok.Data;

/**
 * BOM構造の表示用DTO
 */
@Data
public class BomLazyViewDto {
	
    private int level;              // 階層
    private String itemId;          // 子品番
    private String itemName;        // 子品名
    private String unit;            // 単位
    private String quantityDisp;    // 構成数（親1当り）表示用文字列
    private String remarks;         // 備考

}
