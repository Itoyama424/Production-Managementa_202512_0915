package com.production.management.controller.requirements_planning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.dto.requirements_planning.RequirementsPlannninnResultDto;
import com.production.management.form.lazyload.BomSearchForm;
import com.production.management.service.requirements_planning.RequirementsPlanningService;

@Controller
@RequestMapping("/ProductionManagement")
public class RequirementsPlanningController {

	@Autowired
	private RequirementsPlanningService requirementsPlanningService;

	@GetMapping(value = "/explotion", params = "requirementsPlanninng")
	public String calculateRequirementsPlanning(@Validated @ModelAttribute BomSearchForm form, BindingResult result,
			Model model) {

		RequirementsPlannninnResultDto resultDto = requirementsPlanningService
				.calculateRequirement(form.getParentId(), form.getOrderQty(), form.getBaseDate());
		
		model.addAttribute("res",resultDto);
		
		return "requirements_plunning/requirements_planning.html";
	}
}
