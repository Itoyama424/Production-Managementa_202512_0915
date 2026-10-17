package com.production.management.service.requirements_planning;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.dto.requirements_planning.RequirementsPlanningViewDto;
import com.production.management.dto.requirements_planning.RequirementsPlannninnResultDto;
import com.production.management.entity.BomMasterEntity;
import com.production.management.entity.ItemMasterEntity;
import com.production.management.entity.StockDetailEntity;
import com.production.management.mapper.entity.BomMasterMapper;
import com.production.management.mapper.entity.ItemMasterMapper;
import com.production.management.mapper.entity.StockDetailMapper;
import com.production.management.utility.common.CommonUtility;

import lombok.Data;

@Service
public class RequirementsPlanningService {
	
	@Autowired
	private BomMasterMapper bomMasterMapper;
	
	@Autowired
	private ItemMasterMapper itemMasterMapper;
	
	@Autowired
	private StockDetailMapper stockDetailMapper;
	
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
		// 子部品の所要量(gross)を計算
		Map<String,BigDecimal> grossRequirementMap = calculateRequirementRecursive(rootItemId, orderQty, childListMap);
		// 在庫引当
		resultDto.setRequirementsPlanningList(this.inventoryAllocation(grossRequirementMap));
		
	    return resultDto;
	}
	
	/**
	 * 子部品の所要量(gross)を計算し、Map<String, Long>として返す
	 * @param itemId
	 * @param orderQty
	 * @return Map<String, Long> 子部品IDをキー、所要量(gross)を値とするMap
	 */
	private Map<String,BigDecimal> calculateRequirementRecursive(String itemId, BigDecimal orderQty, Map<String, List<BomMasterEntity>> childListMap) {
		// double enable queue
		Deque<ReqNode> dequeForStack = new ArrayDeque<>();
		
		// push the root node to the stack
		dequeForStack.push(new ReqNode(itemId, orderQty));
		
		// create a map to store the gross requirement for each child item
		Map<String, BigDecimal> grossRequirementMap = new HashMap<>();
		
		while(!dequeForStack.isEmpty()) {
			
			 ReqNode currentNode = dequeForStack.pop();
			 List<BomMasterEntity> childList = childListMap.get(currentNode.getItemId());

			 if(childList == null) {
				 continue;
			 }
			 
			 childList.forEach(childEntity -> {
				 
				 BigDecimal grossQty = childEntity.getQuantity().multiply(currentNode.getQty());
				 grossRequirementMap.merge(childEntity.getItemId(), grossQty, BigDecimal::add);
				 
				 dequeForStack.push(new ReqNode(childEntity.getItemId(), grossQty));
			 });			 			
		}
		
		return grossRequirementMap;
	}
	/**
	 * 在庫引当処理を行い、List<RequirementsPlanningViewDto>として返す
	 * @param grossRequirementMap
	 * @return
	 */
	private List<RequirementsPlanningViewDto> inventoryAllocation(Map<String,BigDecimal> grossRequirementMap) {
		
		List<RequirementsPlanningViewDto> viewList = new ArrayList<>();
		
		grossRequirementMap.forEach((itemId, grossQty) -> {
			
			RequirementsPlanningViewDto viewDto = new RequirementsPlanningViewDto();
			
			ItemMasterEntity itemEntity = itemMasterMapper.getItemMasterByItemId(itemId);

			if (itemEntity != null) {
			    viewDto.setItemId(itemId);
			    viewDto.setItemName(itemEntity.getItemName());
			    viewDto.setUnit(itemEntity.getUnit());
			}

			StockDetailEntity stockDetail = stockDetailMapper.getStockDetailByItemId(itemId);
			
			if(stockDetail == null) {
				stockDetail = new StockDetailEntity();
				stockDetail.setStockQty(BigDecimal.ZERO);
				stockDetail.setDefectQty(BigDecimal.ZERO);
				stockDetail.setHoldQty(BigDecimal.ZERO);
			}
			
			BigDecimal stockQty = stockDetail.getStockQty();
			BigDecimal defectiveQty = stockDetail.getDefectQty();
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



/**
 * 所要量計算（非再帰DFS）
 * @param rootItemId 親品目ID
 * @param orderQty   製造指示数
 * @param baseDate   基準日
 * @return List<RequirementResultDto>
 */
/*public List<RequirementResultDto> calculateRequirement(
        String rootItemId,
        long orderQty,
        LocalDate baseDate) {

    // 結果格納
    Map<String, RequirementResultDto> resultMap = new HashMap<>();

    // DFSスタック
    Deque<ReqNode> stack = new ArrayDeque<>();

    // 親品目をスタックに積む
    stack.push(new ReqNode(rootItemId, orderQty));

    while (!stack.isEmpty()) {

        ReqNode current = stack.pop();
        String itemId = current.itemId;
        long parentNetQty = current.netQty; // 親の純所要量（初回は製造指示数）

        // 子部品一覧を取得
        List<BomMasterEntity> children = childrenListByParentMap.get(itemId);
        if (children == null) continue;

        for (BomMasterEntity child : children) {

            String childId = child.getItemId();
            long qtyPerParent = child.getQuantity();

            // Gross必要量 = 親のNet × 構成数
            long gross = parentNetQty * qtyPerParent;

            // 在庫情報取得
            StockInfo stock = stockMapper.getStock(childId, baseDate);
            long stockQty = stock.getStockQty();
            long badQty   = stock.getBadQty();
            long holdQty  = stock.getHoldQty();

            long effectiveStock = stockQty - badQty - holdQty;
            if (effectiveStock < 0) effectiveStock = 0;

            // Net必要量 = Gross − 有効在庫
            long net = gross - effectiveStock;
            if (net < 0) net = 0;

            // 結果をマージ（同じ部品が複数経路に出ても合算）
            RequirementResultDto dto = resultMap.getOrDefault(childId, new RequirementResultDto(childId));
            dto.addGross(gross);
            dto.addStock(stockQty);
            dto.addBad(badQty);
            dto.addHold(holdQty);
            dto.addEffective(effectiveStock);
            dto.addNet(net);
            resultMap.put(childId, dto);

            // Netがある場合のみ次階層へ深掘り
            if (net > 0) {
                stack.push(new ReqNode(childId, net));
            }
        }
    }

    return new ArrayList<>(resultMap.values());
}

*//**
	* DFS用ノード
	*/
/*
private static class ReqNode {
 String itemId;
 long netQty;
 ReqNode(String itemId, long netQty) {
     this.itemId = itemId;
     this.netQty = netQty;
 }
}
*/