package com.production.management.dto.requirements_planning;

import java.math.BigDecimal;
import java.util.List;

import com.production.management.entity.ItemMasterEntity;

import lombok.Data;

@Data
public class RequirementsPlannninnResultDto {

	private ItemMasterEntity productionOrder;
	
	private BigDecimal orderQty; 
	
	private List<RequirementsPlanningViewDto> requirementsPlanningList;
}
