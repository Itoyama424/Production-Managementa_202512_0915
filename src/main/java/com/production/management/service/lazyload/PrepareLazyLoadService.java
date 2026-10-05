package com.production.management.service.lazyload;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.entity.BomMasterEntity;
import com.production.management.mapper.lazyload.BomMasterMapper;


@Service
public class PrepareLazyLoadService {

	@Autowired
	private BomMasterMapper bomMasterMapper;
	
	/**
	 * 部材のぶら下がり構造をMapに変換する
	 * @return Map<String, List<BomMasterEntity>>  変換後のMap
	 */
	public Map<String,List<BomMasterEntity>> prapareExplosionLazyLoad() {
		
		 LocalDate baseDate = LocalDate.of(2026, 10, 1);
		 List<BomMasterEntity> bonRawList = bomMasterMapper.getRawBomList(baseDate);
		 
		 return convertToMap(bonRawList);
	}
	
	/**
	 * 一つの部材にぶら下がる部材のリストをMapに変換する
	 * @param bonRawList
	 * @return Map<String, List<BomMasterEntity>>  変換後のMap
	 */
	private Map<String, List<BomMasterEntity>> convertToMap(List<BomMasterEntity> bonRawList) {
		
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
