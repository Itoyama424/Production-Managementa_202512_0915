package com.production.management.service.requirements_planning;

public class Sampleservice {
	/*
	
	*//**
		* BOMの構成が品目ごとに一貫しているかを高速に検証する。
		* 
		* 例：
		*  B → {E, F}
		*  B → {E, F}   → OK
		*  B → {E, G}   → NG（構成不一致）
		*/
	/*
	public static List<String> validateConsistentBomStructure(List<BomMasterEntity> bomList) {
	
	 // childItemId → 構成セット（親と数量の組）
	 Map<String, Set<String>> structureMap = new HashMap<>();
	
	 List<String> errors = new ArrayList<>();
	
	 for (BomMasterEntity bom : bomList) {
	     String parent = bom.getParentItemId();
	     String child = bom.getItemId();
	     long qty = bom.getQuantity();
	
	     // 構成を一意に表すキー（親＋数量）
	     String structureKey = parent + ":" + qty;
	
	     // 初回登録
	     structureMap.putIfAbsent(child, new HashSet<>());
	     Set<String> set = structureMap.get(child);
	
	     // 既に別構成が登録されているかチェック
	     if (!set.isEmpty() && !set.contains(structureKey)) {
	         errors.add("構成不一致: child=" + child + 
	                    " 既存=" + set + 
	                    " 新規=" + structureKey);
	     }
	
	     // 構成を追加
	     set.add(structureKey);
	 }
	
	 return errors;
	}
	
	*//**
		* 所要量計算（正しい名古屋方式：2段階DFS）
		*/
	/*
	public List<RequirementResultDto> calculateRequirement(
	     String rootItemId,
	     long orderQty,
	     LocalDate baseDate) {
	
	 // ① Gross集計フェーズ（在庫引当しない）
	 Map<String, Long> grossMap = new HashMap<>();
	 Deque<ReqNode> stack = new ArrayDeque<>();
	 stack.push(new ReqNode(rootItemId, orderQty));
	
	 while (!stack.isEmpty()) {
	     ReqNode current = stack.pop();
	     String itemId = current.itemId;
	     long parentQty = current.qty;
	
	     List<BomMasterEntity> children = childrenListByParentMap.get(itemId);
	     if (children == null) continue;
	
	     for (BomMasterEntity child : children) {
	         String childId = child.getItemId();
	         long qtyPerParent = child.getQuantity();
	
	         long gross = parentQty * qtyPerParent;
	
	         // Grossを合算（ルート違いでもまとめる）
	         grossMap.merge(childId, gross, Long::sum);
	
	         // 次階層へ（まだNet計算しない）
	         stack.push(new ReqNode(childId, gross));
	     }
	 }
	
	 // ② 在庫引当フェーズ（1回だけ）
	 Map<String, RequirementResultDto> resultMap = new HashMap<>();
	
	 for (Map.Entry<String, Long> entry : grossMap.entrySet()) {
	     String itemId = entry.getKey();
	     long gross = entry.getValue();
	
	     StockInfo stock = stockMapper.getStock(itemId, baseDate);
	     long stockQty = stock.getStockQty();
	     long badQty   = stock.getBadQty();
	     long holdQty  = stock.getHoldQty();
	
	     long effectiveStock = Math.max(0, stockQty - badQty - holdQty);
	     long net = Math.max(0, gross - effectiveStock);
	
	     RequirementResultDto dto = new RequirementResultDto(itemId);
	     dto.setGross(gross);
	     dto.setStock(stockQty);
	     dto.setBad(badQty);
	     dto.setHold(holdQty);
	     dto.setEffective(effectiveStock);
	     dto.setNet(net);
	
	     resultMap.put(itemId, dto);
	 }
	
	 return new ArrayList<>(resultMap.values());
	}
	
	*//** DFS用ノード */
	/*
	private static class ReqNode {
	String itemId;
	long qty;
	ReqNode(String itemId, long qty) {
	this.itemId = itemId;
	this.qty = qty;
	}
	}
	
	*/}
