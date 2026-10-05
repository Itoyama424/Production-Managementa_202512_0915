package com.production.management.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemMasterEntity implements Serializable {

    private String itemId;     // 品目ID（主キー）
    private String itemName;   // 品目名称
    private Integer itemType;  // 品目タイプ（1:完成品, 2:アッセンブリ, 3:単体部品・材料）
    private String unit;       // 単位（台, 個, kg, m）
}
