package com.production.management.service.requirements_planning;

public class SampleService2 {
	/*
public BomExplosionResult processLazyLoad(String itemId, LocalDate baseDate, int level) {

    // ★必要な親だけ SQL で取る（全件ではない）
    List<BomMasterEntity> childrenList =
        bomMasterMapper.getChildrenByParent(itemId, baseDate);

    List<BomLazyViewDto> viewList = new ArrayList<>();
    for (BomMasterEntity child : childrenList) {
        viewList.add(convertToViewDto(child, level));
    }

    BomExplosionResult result = new BomExplosionResult();
    result.setBomExplosionList(viewList);
    return result;
}
	
	
	*//**
		* 指定ルートからBOMを辿り、同一ITEMが複数回現れた場合にサブ構成が同一かチェックする
		* @param rootItemId チェック開始のルートアイテムID
		* @param baseDate 基準日
		* @return 不整合メッセージのリスト。空リストなら整合
		*/
	/*
	public List<String> validateBomConsistency(String rootItemId, LocalDate baseDate) {
	
	 // children map を作成
	 this.childrenListByParentMap = this.getChildrenListByParentMap(baseDate);
	
	 List<String> errors = new ArrayList<>();
	
	 // アイテムID -> 正規化された部分ツリー表現 のキャッシュ
	 Map<String, String> subtreeCache = new HashMap<>();
	 // 再帰中のサイクル検出用
	 Set<String> visiting = new HashSet<>();
	
	 // ルートから再帰的に正規化表現を生成しつつ整合性をチェック
	 computeCanonicalSubtree(rootItemId, subtreeCache, visiting, errors);
	
	 return errors;
	}
	
	*//**
		* 指定アイテムの部分ツリーを正規化して文字列で返す（キャッシュ利用）
		* 同じ itemId が別経路で現れたときに既存キャッシュと比較して不整合を errors に追加する
		*/
	/*
	private String computeCanonicalSubtree(String itemId,
	                                    Map<String, String> cache,
	                                    Set<String> visiting,
	                                    List<String> errors) {
	
	 // 既にキャッシュがあればそれを返す
	 if (cache.containsKey(itemId)) {
	     return cache.get(itemId);
	 }
	
	 // サイクル検出
	 if (visiting.contains(itemId)) {
	     String cycleMsg = "サイクル検出: itemId=" + itemId;
	     errors.add(cycleMsg);
	     return "<CYCLE:" + itemId + ">";
	 }
	
	 visiting.add(itemId);
	
	 List<BomMasterEntity> children = childrenListByParentMap.get(itemId);
	
	 // 子がいなければ葉ノードの表現を返す
	 if (children == null || children.isEmpty()) {
	     String leaf = "LEAF(" + itemId + ")";
	     cache.put(itemId, leaf);
	     visiting.remove(itemId);
	     return leaf;
	 }
	
	 // 各子について「childItemId:quantity:childCanonical」を作り、ソートして正規化
	 List<String> childReprs = new ArrayList<>();
	 for (BomMasterEntity child : children) {
	     String childId = child.getItemId();
	     String qty = child.getQuantity() == null ? "null" : child.getQuantity().toString();
	
	     // 再帰で子の部分ツリー表現を取得
	     String childSubtree = computeCanonicalSubtree(childId, cache, visiting, errors);
	
	     // 子の表現に数量を含める。数量を比較対象にしたくない場合は qty を除く
	     String repr = childId + ":" + qty + ":" + childSubtree;
	     childReprs.add(repr);
	 }
	
	 // ソートして順序依存性を排除
	 Collections.sort(childReprs);
	
	 // 正規化表現を作成
	 String canonical = "NODE(" + itemId + ")[" + String.join(",", childReprs) + "]";
	
	 // キャッシュに保存する前に、既に別経路でキャッシュされている場合は比較して不整合を記録
	 String existing = cache.get(itemId);
	 if (existing != null && !existing.equals(canonical)) {
	     String msg = String.format("不整合: itemId=%s の部分ツリーが異なります。既存=%s 新=%s", itemId, existing, canonical);
	     errors.add(msg);
	 } else {
	     cache.put(itemId, canonical);
	 }
	
	 visiting.remove(itemId);
	 return canonical;
	}
	
	
	*/}
