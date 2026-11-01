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
import com.production.management.form.requirementsplanning.RequiredPlanningForm;
import com.production.management.service.requirements_planning.RequirementsPlanningService;

@Controller
@RequestMapping("/productionmanagement")
public class RequirementsPlanningController {

	@Autowired
	private RequirementsPlanningService requirementsPlanningService;

	@GetMapping(value = "/requirementsplanninng/explosion")
	public String calculateRequirementsPlanning(@Validated @ModelAttribute RequiredPlanningForm form, BindingResult result,
			Model model) {
		
		// 入力チェック
		if (result.hasErrors()) {
			return "/requirements_planning/requirements_planning_init";
		}

		RequirementsPlannninnResultDto resultDto = requirementsPlanningService
				.calculateRequirement(form.getParentId(), form.getOrderQty(), form.getBaseDate());
		
		model.addAttribute("res",resultDto);
		
		return "/requirements_planning/requirements_planning.html";
	}
}
