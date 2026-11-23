package com.production.management.form.lazyload;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BomSearchForm {
	
    @NotBlank(message = "IDは必須です")
    @Pattern(regexp = "^[A-Z0-9-_]+$", message = "英数字のみ入力可能です")
    private String parentId;

    @NotNull(message = "基準日を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // HTMLのdate型から変換するために必要
    private LocalDate baseDate;
    
}
