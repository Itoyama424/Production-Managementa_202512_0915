package com.production.management.utility.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.production.management.entity.BomMasterEntity;

public class CommonUtility {

	/**
	 * 一つの部材にぶら下がる部材のリストをMapに変換する
	 * @param bonRawList
	 * @return Map<String, List<BomMasterEntity>>  変換後のMap
	 */
	public static Map<String, List<BomMasterEntity>> convertToMap(List<BomMasterEntity> bonRawList) {
		
		Map<String, List<BomMasterEntity>> bomMap = new HashMap<>();
		
		bonRawList.forEach(bom -> {
			String parentId = bom.getParentId();
			if (!bomMap.containsKey(parentId)) {
				bomMap.put(parentId, new java.util.ArrayList<>());
			}
			bomMap.get(parentId).add(bom);
		});
		
		return bomMap;
	}
}
