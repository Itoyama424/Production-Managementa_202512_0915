package com.production.management.controller.manufacturing_order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.constant.ManufacturingOrderStatus;
import com.production.management.entity.ItemMasterEntity;
import com.production.management.form.manufacture_order.ManufactureOrderForm;
import com.production.management.service.entity.EntityService;
import com.production.management.service.manufacturing_order.ManufacturingOrderService;

@Controller
@RequestMapping("/productionmanagement")
public class ManufacturingOrderController {
   
   @Autowired
   private ManufacturingOrderService manufacturingOrderService;
   
   @Autowired
   private EntityService entityService;
   /**
    * 製造指示初期表示
    * 
    * @return
    */
   @GetMapping(value = "/init/manufactureorder")
   public String initManufactureOrder(@ModelAttribute ManufactureOrderForm form, Model model) {
      
       // モデルに製造指示初期表示用の属性を設定する
      setModelAttributes(model);
      
      return "/manufacturing_order/manufacturing_order";
   }
   
   /**
    * 製造指示登録処理
    * 
    * @param form
    * @param result
    * @param model
    * @return
    */
   @PostMapping(value = "/manufactureorder/register")
   public String manufacturingOrderRegist(@Validated @ModelAttribute ManufactureOrderForm  form, BindingResult result, Model model) {
      
       // 入力チェック
       if (result.hasErrors()) {
           // モデルに製造指示初期表示用の属性を設定する
           setModelAttributes(model);
           
           return "/manufacture_order/manufacture_order";
       }
      
       setModelAttributes(model);
       // 製造指示登録処理を実行する
       manufacturingOrderService.registerManufactureOrder(form);
       
       return "redirect:/productionmanagement/init/manufactureorderlist";
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
