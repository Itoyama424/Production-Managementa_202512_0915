package com.production.management.service.requirements_planning;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.production.management.dto.requirements_planning.RequirementsPlanningViewDto;

@Service
public class RequirementsPlanningService {
	
	public List<RequirementsPlanningViewDto> calculateRequirement(
	        String rootItemId,
	        long orderQty,
	        LocalDate baseDate) {
		
		//Deque<ReqNode> 
		
		
		List<RequirementsPlanningViewDto> viewList = new ArrayList<>();
		
		
		return viewList;
	}
	
	/**
	 * 子部品の所要量(gross)を計算し、Map<String, Long>として返す
	 * @param itemId
	 * @param orderQty
	 * @return Map<String, Long> 子部品IDをキー、所要量(gross)を値とするMap
	 */
	private Map<String,Long> calculateRequirementRecursive(String itemId, long orderQty) {
		// double enable queue
		Deque<ReqNode> dequeForStack = new ArrayDeque<>();
		// push the root node to the stack
		dequeForStack.push(new ReqNode(itemId, orderQty));
		// create a map to store the gross requirement for each child item
		Map<String, Long> grossMap = new HashMap<>();
		while(!dequeForStack.isEmpty()) {
			
		}
		
		return new HashMap<>();
	}
	
	/** depth first search DFS用ノード */
	private static class ReqNode {
	    String itemId;
	    long qty;
	    ReqNode(String itemId, long qty) {
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