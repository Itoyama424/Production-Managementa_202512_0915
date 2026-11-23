package com.production.management.controller.inventory_allocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.entity.ManufactureOrderEntity;
import com.production.management.form.inventory_allocation.ManufactureOrderSearchForm;
import com.production.management.service.inventory_allocation.InventoryAllocationService;


@Controller
@RequestMapping("/productionmanagement")
public class InventoryAllocationController {
    
    @Autowired
    private InventoryAllocationService inventoryAllocationService;
    /**
     * 在庫引当初期表示
     * 
     * @return
     */
    @GetMapping(value = "/init/inventoryallocation")
    public String initInventoryallocation(@ModelAttribute("manufactureOrderSearchForm") ManufactureOrderSearchForm form, Model model) {
        
        this.setModelAttributes(model);
        return "/inventory_allocation/inventory_allocation";
    }  
    
    /**
     * モデルに製造指示初期表示用の属性を設定する
     * 
     * @param model
     */
    private void setModelAttributes(Model model) {
        // 製造指示Noリストを取得
        List<ManufactureOrderEntity> orderNoList = inventoryAllocationService.getValidMoNoList();
        // モデルに製造指示Noリストを設定
        model.addAttribute("orderNoList", orderNoList);

    }
}
