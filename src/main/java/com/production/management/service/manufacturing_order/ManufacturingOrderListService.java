package com.production.management.service.manufacturing_order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.management.dto.manufacturing_order.ManufacturingOrderListResult;
import com.production.management.dto.manufacturing_order.ManufacturingOrderListViewDto;
import com.production.management.form.manufacture_order.ManufactureOrderListForm;
import com.production.management.mapper.manufacturing_order.ManufacturingOrderListMapper;

/**
 * 製造指示一覧画面のサービス
 */
@Service
public class ManufacturingOrderListService {

    @Autowired
    private ManufacturingOrderListMapper manufacturingOrderListMapper;
    
    public ManufacturingOrderListResult getManufactureorderList(ManufactureOrderListForm form) {
        
        // 製造指示一覧結果DTOを作成
        ManufacturingOrderListResult orderListResult = new ManufacturingOrderListResult();        
        // 製造指示一覧を取得
        List<ManufacturingOrderListViewDto> orderList = manufacturingOrderListMapper.selectManufactureOrderList(
                form.getMoNo(), form.getItemId(), form.getStartDateFrom(), form.getStartDateTo(), form.getStatus());
        
        // 製造指示一覧結果DTOに製造指示一覧を設定
        orderListResult.setManufactureOrderList(orderList);
        // 製造指示一覧結果DTOに総件数を設定
        orderListResult.setTotalCount(orderList.size());
        
        return orderListResult;
    }
}
