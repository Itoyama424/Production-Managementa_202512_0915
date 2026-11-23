package com.production.management.service.inventory_allocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.constant.ManufacturingOrderStatus;
import com.production.management.entity.ManufactureOrderEntity;
import com.production.management.mapper.inventory_allocation.InventoryAllocationMapper;

@Service
public class InventoryAllocationService {
    /**
     * 在庫引当のMapperインターフェース
     */
    @Autowired
    public InventoryAllocationMapper inventoryAllocationMapper;
    /**
     * 製造指示リストを取得
     * @return 製造指示リスト
     */
    public List<ManufactureOrderEntity> getValidMoNoList() {
        return inventoryAllocationMapper.findValidMoNoList(ManufacturingOrderStatus.NOT_STARTED.name());
    }
}
