package com.production.management.service.requirements_planning;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.dto.requirements_planning.RequirementsPlanningViewDto;
import com.production.management.dto.requirements_planning.RequirementsPlannninnResultDto;
import com.production.management.entity.BomMasterEntity;
import com.production.management.entity.ItemMasterEntity;
import com.production.management.entity.StockEntity;
import com.production.management.mapper.entity.BomMasterMapper;
import com.production.management.mapper.entity.ItemMasterMapper;
import com.production.management.mapper.entity.StockMapper;
import com.production.management.utility.common.CommonUtility;

import lombok.Data;

@Service
public class RequirementsPlanningService {
	
	@Autowired
	private BomMasterMapper bomMasterMapper;
	
	@Autowired
	private ItemMasterMapper itemMasterMapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	/**
	 * 所要量計算（非再帰DFS）
	 * @param rootItemId
	 * @param orderQty
	 * @param baseDate
	 * @return
	 */
	public RequirementsPlannninnResultDto calculateRequirement(
	        String rootItemId,
	        BigDecimal orderQty,
	        LocalDate baseDate) {
		
		// 結果格納用DTO
		RequirementsPlannninnResultDto resultDto = new RequirementsPlannninnResultDto();
		// 製造指示製品の情報を取得
		resultDto.setProductionOrder(itemMasterMapper.getItemMasterByItemId(rootItemId));
		// 製造指示数を設定
		resultDto.setOrderQty(orderQty);
		
		List<BomMasterEntity> effectiveBomList = bomMasterMapper.getRawByEffectiveBomList(baseDate);
		// 子部品のリストをMap<String, List<BomMasterEntity>>に変換
		Map<String, List<BomMasterEntity>> childListMap = CommonUtility.convertToMap(effectiveBomList);
		
	    // create a map to store the gross requirement for each child item
        Map<String, BigDecimal> grossRequirementMap = new LinkedHashMap<>();
		// 子部品の所要量(gross)を計算
		this.calculateRequirementRecursive(rootItemId, orderQty, grossRequirementMap, childListMap);
		// 在庫引当
		resultDto.setRequirementsPlanningList(this.calculateInventoryStatus(grossRequirementMap));
		
	    return resultDto;
	}
	
	/**
	 * 子部品の所要量(gross)を計算し、Map<String, Long>として返す
	 * @param itemId
	 * @param orderQty
	 * @return Map<String, Long> 子部品IDをキー、所要量(gross)を値とするMap
	 */
	private void calculateRequirementRecursive(String itemId, BigDecimal qty, 
	        Map<String, BigDecimal> grossRequirementMap, Map<String, List<BomMasterEntity>> childListMap) {
	    
	    List<BomMasterEntity> childList = childListMap.get(itemId);

	    if (childList == null) {
	        return;
	    }
	    
	    for (BomMasterEntity child : childList) {
	        
	        BigDecimal grossQty =
	                child.getQuantity().multiply(qty);

	        grossRequirementMap.merge(
	                child.getItemId(),
	                grossQty,
	                BigDecimal::add);
	        
	        calculateRequirementRecursive(
	                child.getItemId(),
	                grossQty,
	                grossRequirementMap,
	                childListMap);
	        
	    }
	}
	/**
	 * 在庫引当処理を行い、List<RequirementsPlanningViewDto>として返す
	 * @param grossRequirementMap
	 * @return
	 */
	private List<RequirementsPlanningViewDto> calculateInventoryStatus(Map<String,BigDecimal> grossRequirementMap) {
		
		List<RequirementsPlanningViewDto> viewList = new ArrayList<>();
		
		grossRequirementMap.forEach((itemId, grossQty) -> {
			
			RequirementsPlanningViewDto viewDto = new RequirementsPlanningViewDto();
			
			ItemMasterEntity itemEntity = itemMasterMapper.getItemMasterByItemId(itemId);

			if (itemEntity != null) {
			    viewDto.setItemId(itemId);
			    viewDto.setItemName(itemEntity.getItemName());
			    viewDto.setUnit(itemEntity.getUnit());
			}

			StockEntity stockDetail = stockMapper.getStockDetailByItemId(itemId);
			
			if(stockDetail == null) {
				stockDetail = new StockEntity();
				stockDetail.setStockQty(BigDecimal.ZERO);
				stockDetail.setDefectiveQty (BigDecimal.ZERO);
				stockDetail.setHoldQty(BigDecimal.ZERO);
			}
			
			BigDecimal stockQty = stockDetail.getStockQty();
			BigDecimal defectiveQty = stockDetail.getDefectiveQty();
			BigDecimal holdQty = stockDetail.getHoldQty();
			// 有効在庫 = 在庫 - 不良 - 保留
			BigDecimal avaivalableQty = stockQty.subtract(defectiveQty).subtract(holdQty);
			// 不足数 = 総必要数 - 有効在庫
			BigDecimal shortageQty = grossQty.subtract(avaivalableQty);			
			
			if(shortageQty.compareTo(BigDecimal.ZERO) < 0) {
				shortageQty = BigDecimal.ZERO;
			}
			// 総必要量
			viewDto.setTotalRequiredDisp(grossQty.stripTrailingZeros().toPlainString());
			// 有効在庫
			viewDto.setAvailableQtyDisp(avaivalableQty.stripTrailingZeros().toPlainString());
			// 在庫数
			viewDto.setStockQtyDisp(stockDetail.getStockQty().stripTrailingZeros().toPlainString());
			// 不足数
			viewDto.setShortageQtyDisp(shortageQty.stripTrailingZeros().toPlainString());
			// 不良数
			viewDto.setDefectiveQtyDisp(defectiveQty.stripTrailingZeros().toPlainString());
			// 保留数
			viewDto.setHoldQtyDisp(holdQty.stripTrailingZeros().toPlainString());
			
			viewList.add(viewDto);
		});
		
		return viewList;
	}
	/** depth first search DFS用ノード */
	@Data
	private static class ReqNode {
	    String itemId;
	    BigDecimal qty;
	    ReqNode(String itemId, BigDecimal qty) {
	        this.itemId = itemId;
	        this.qty = qty;
	    }
	}
}

