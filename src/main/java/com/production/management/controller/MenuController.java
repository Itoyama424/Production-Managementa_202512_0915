package com.production.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.form.lazyload.BomSearchForm;
import com.production.management.form.requirementsplanning.RequiredPlanningForm;

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
	 * BomLazy展開初期表示
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping("/init/bomlazyload")
	public String initBomLazyLoad(@ModelAttribute BomSearchForm form) {
		return "bom/bom_init_lazy_exlosion";
	}

	/**
	 * 所要量計算初期表示
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping("/init/requirementplanning")
	public String initRequirementPlanning(@ModelAttribute RequiredPlanningForm form) {
		return "requirements_planning/requirements_planning_init";
	}
	/**
	 * 製造指示初期表示
	 * 
	 * @return
	 */
	@GetMapping(value = "/init/manufactureorder")
	public String initManufactureOrder() {
		return "/manufacture_order/manufacture_order";
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
