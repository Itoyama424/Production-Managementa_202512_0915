package com.production.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.form.lazyload.BomSearchForm;

@Controller
@RequestMapping("/productionmanagement")
public class MenuController {

	/**
	 * メニュー表示を行う
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping("/menu")
	public String index(@ModelAttribute BomSearchForm form) {
		return "/menu";
	}

	   /**
	    * モデルに製造指示初期表示用の属性を設定する
	    * 
	    * @param model
	    */
	   private void setModelAttributes(Model model) {
	      // 製造指示初期表示用に、調達区分が「M」の部品マスタを取得する
//	      List<ItemMasterEntity> itemList =  entityService.getItemMasterList();
//	      model.addAttribute("itemList", itemList);
//	      // 製造指示のステータス一覧を取得する
//	      model.addAttribute("statusList", ManufacturingOrderStatus.values());
	   }
}
