package com.production.management.service.lazyload;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.dto.lazyload.BomExplosionResult;
import com.production.management.dto.lazyload.BomLazyViewDto;
import com.production.management.entity.BomMasterEntity;
import com.production.management.entity.ItemMasterEntity;
import com.production.management.mapper.lazyload.BomMasterMapper;
import com.production.management.mapper.lazyload.ItemMasterMapper;

@Service
public class ExplosionLazyLoadService {
	
	@Autowired
	private BomMasterMapper bomMasterMapper;
	
	@Autowired
	private ItemMasterMapper itemMasterMapper;
	
    /**
     * BOM構造を展開する（Lazy Load）
     * @param itemId 部品ID
     * @param baseDate 基準日
     * @param level 展開レベル
     * @return
     */
	public BomExplosionResult processLazyLoad(String itemId,  LocalDate baseDate, int level) {
		
		BomExplosionResult explosionResult = new BomExplosionResult();
		
		ItemMasterEntity itemMaster = itemMasterMapper.getItemMasterByItemId(itemId);
		
		// 展開結果のアイテム名を設定する
		explosionResult.setItemName(itemMaster.getItemName());
		// 展開結果のアイテムIDを設定する
		explosionResult.setItemId(itemId);
		// 展開結果の基準日を設定する
		explosionResult.setBaseDate(baseDate);
		// 展開結果を設定する
		explosionResult.setBomExplosionList(this.expandBomStructure(itemId, baseDate, level));
		
		return explosionResult;
		
	}
	
	/**
	 * BOM構造を展開する
	 * @param parentId
	 * @return
	 */
	private List<BomLazyViewDto> expandBomStructure(String parentId, LocalDate baseDate, int level) {
		
		List<BomMasterEntity> childrenList = bomMasterMapper.getRawBomList(parentId, baseDate);
		
		List<BomLazyViewDto> bomLazyViewList = new ArrayList<>();
		if (childrenList != null) {
			for (BomMasterEntity child : childrenList) {
				bomLazyViewList.add(this.convertToViewDto(child, level)); 
			}
		}
		
		return bomLazyViewList;
	}
	
	/**
	 * BomMasterEntityをBomLazyViewDtoに変換する
	 * @param bom
	 * @param level
	 * @return BomLazyViewDto
	 */
	private BomLazyViewDto convertToViewDto(BomMasterEntity bomEntity, int level) {
		
		ItemMasterEntity itemMaster = itemMasterMapper.getItemMasterByItemId(bomEntity.getItemId());
		
		BomLazyViewDto bomLazyViewDto = new BomLazyViewDto();
		bomLazyViewDto.setLevel(level);
		bomLazyViewDto.setItemName(itemMaster.getItemName());
		bomLazyViewDto.setUnit(itemMaster.getUnit());
		
		bomLazyViewDto.setItemId(bomEntity.getItemId());
		bomLazyViewDto.setQuantity(bomEntity.getQuantity());
		bomLazyViewDto.setQuantityDisp(bomEntity.getQuantity().toString());
		
		return bomLazyViewDto;
	}
}
