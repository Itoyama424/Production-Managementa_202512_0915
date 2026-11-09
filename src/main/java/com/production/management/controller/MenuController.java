package com.production.management.controller;

import org.springframework.stereotype.Controller;
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
	 * 在庫引当初期表示
	 * 
	 * @return
	 */
	@GetMapping(value = "/init/inventoryallocation")
	public String initInventoryallocation() {
		return "/inventory_allocation/inventory_allocation";
	}	
}
