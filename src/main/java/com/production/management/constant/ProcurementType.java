package com.production.management.constant;

import lombok.Getter;

/**
 * 調達区分の定数クラス
 */
@Getter	
public enum ProcurementType {

	MANUFACTURE("M", "製造"),
	PURCHASE("P", "購入"),
	SUBCNTRACT("S", "外注");
	
	private final String code;
	private final String name;
	
	ProcurementType(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
