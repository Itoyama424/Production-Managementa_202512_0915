package com.production.management.controller.manufacturing_order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.constant.ManufacturingOrderStatus;
import com.production.management.dto.manufacturing_order.ManufacturingOrderListResult;
import com.production.management.entity.ItemMasterEntity;
import com.production.management.form.manufacture_order.ManufactureOrderListForm;
import com.production.management.service.entity.EntityService;
import com.production.management.service.manufacturing_order.ManufacturingOrderListService;

/**
 * 製造指示一覧画面のコントローラー
 */
@RequestMapping("/productionmanagement")
@Controller
public class ManufacturingOrderListController {
    
    @Autowired
    private ManufacturingOrderListService manufacturingOrderListService;
    @Autowired
    private EntityService entityService;
    
    /**
     * 製造指示一覧画面の初期表示
     * @param form
     * @param result
     * @param model
     * @return 画面情報
     */
    @GetMapping(value = "/init/manufactureorderlist")
    public String initManufacturingOrderList(@Validated @ModelAttribute("manufactureOrderListForm") ManufactureOrderListForm form, BindingResult result, Model model) {
        
        // 製造指示情報を取得する
        ManufacturingOrderListResult manufacturingOrderListResult = manufacturingOrderListService.getManufactureorderList(form);
        // 製造指示情報をモデルに設定する
        model.addAttribute("orderListResult", manufacturingOrderListResult);
        // モデルに製造指示初期表示用の属性を設定する
        this.setModelAttributes(model);
        
        return "/manufacturing_order/manufacturing_order_list";
    }
    
    /**
     * モデルに製造指示初期表示用の属性を設定する
     * 
     * @param model
     */
    private void setModelAttributes(Model model) {
       // 製造指示初期表示用に、調達区分が「M」の部品マスタを取得する
       List<ItemMasterEntity> itemList =  entityService.getItemMasterList();
       model.addAttribute("itemList", itemList);
       // 製造指示のステータス一覧を取得する
       model.addAttribute("statusList", ManufacturingOrderStatus.values());
    }
    
}
