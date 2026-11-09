package com.production.management.constant;

/**
 * 製造指示のステータスを表す列挙型
 */
public enum ManufacturingOrderStatus {

    NOT_STARTED("未開始"),
    STARTED("開始済"),
    COMPLETED("完了");

    private final String label;

    ManufacturingOrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
