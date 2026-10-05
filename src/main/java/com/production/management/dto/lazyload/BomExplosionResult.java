package com.production.management.dto.lazyload;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class BomExplosionResult {

	// 部品名
	private String itemName;
	// 部品ID
	private String itemId;
	// 基準日
	private LocalDate baseDate;
	// 展開結果
	private List<BomLazyViewDto> bomExplosionList;

}
