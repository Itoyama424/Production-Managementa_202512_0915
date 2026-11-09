package com.production.management.service.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.constant.ProcurementType;
import com.production.management.entity.ItemMasterEntity;
import com.production.management.mapper.entity.ItemMasterMapper;

/**
 * エンティティサービスクラス
 */
@Service
public class EntityService {

    @Autowired
    private ItemMasterMapper itemMasterMapper;
    
    /**
     * 製造指示の初期表示用に、調達区分が「M」の部品マスタを取得する
     * @return
     */
    public List<ItemMasterEntity> getItemMasterList() {
      
      return itemMasterMapper.getItemMasterListByProcurementTypeM(ProcurementType.MANUFACTURE.getCode());
    }
}
