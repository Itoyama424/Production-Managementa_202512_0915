package com.production.management.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.production.management.constant.ManufacturingOrderStatus;

import lombok.Data;

@Data
public class ManufactureOrderEntity {

    /** 製造指示番号 */
    private String moNo;

    /** 製造品目 */
    private String itemId;

    /** 製造数量 */
    private Integer orderQty;

    /** 製造開始予定日 */
    private LocalDate startDate;

    /** 納期 */
    private LocalDate dueDate;

    /** ステータス */
    private ManufacturingOrderStatus status;

    /** 備考 */
    private String remarks;

    /** 登録日時 */
    private LocalDateTime createdAt;

    /** 更新日時 */
    private LocalDateTime updatedAt;
}