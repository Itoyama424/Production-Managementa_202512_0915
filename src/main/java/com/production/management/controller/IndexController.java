package com.production.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.form.lazyload.BomSearchForm;

@Controller
@RequestMapping("/bom")
public class IndexController {

	  /**
	   * 初期表示を行う
	   * 
	   * @param form
	   * @return
	   */
	  @GetMapping("/index")
	  public String index(@ModelAttribute BomSearchForm form) {
	    return "/bom/index";
	  }
}
