package com.production.management.form.manufacture_order;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.production.management.constant.ManufacturingOrderStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * 製造指示登録フォーム
 */
@Data
public class ManufactureOrderForm {

    @NotBlank(message = "部品IDを選択してください。")
    private String itemId;

    @NotNull(message = "生産指示数を入力してください。")
    @Min(value = 1, message = "1個以上で入力してください。")
    private Integer orderQty;

    @NotNull(message = "開始日を入力してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @NotNull(message = "ステータスを選択してください。")
    private ManufacturingOrderStatus status;

    @NotNull(message = "納期を入力してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Size(max = 200)
    private String remarks;
}