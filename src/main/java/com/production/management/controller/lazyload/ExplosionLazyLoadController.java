package com.production.management.controller.lazyload;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.production.management.dto.lazyload.BomExplosionResult;
import com.production.management.form.lazyload.BomSearchForm;
import com.production.management.service.lazyload.ExplosionLazyLoadService;

@Controller
@RequestMapping("/bom")
public class ExplosionLazyLoadController {

	@Autowired
	private ExplosionLazyLoadService explosionLazyLoadService;


	@GetMapping(value = "explotion")
	public String initLazyLoad(@Validated @ModelAttribute BomSearchForm form, BindingResult result, Model model) {
		// 入力チェック
		if (result.hasErrors()) {
			return "bom/index";
		}
		// BOM構造の展開処理を実行する
		BomExplosionResult explosionResult = 
				explosionLazyLoadService.processLazyLoad(form.getParentId(),form.getBaseDate(), 0);
		// 展開結果をモデルに設定する
		model.addAttribute("explosionResult", explosionResult);
		// BOM構造の展開結果を表示するためのビューを返す
		return "/bom/bom_display_lazyload";
	}
	
	@GetMapping(value="explosionChildren")
	public String explodeChildren(
			@RequestParam("itemId") String itemId, 
			@RequestParam("baseDate") LocalDate baseDate,
			@RequestParam("level") int level,
			Model model
			) {
		
		// 子品目の展開処理を実行する
		BomExplosionResult explosionResult = explosionLazyLoadService.processLazyLoad(itemId, baseDate, ++level);
		// 展開結果をモデルに設定する
		model.addAttribute("explosionResult", explosionResult);
		// 部品構成の展開結果を表示するためのビューを返す
		return "/bom/bom_display_lazyload::bomRowFragment";
	}
}
