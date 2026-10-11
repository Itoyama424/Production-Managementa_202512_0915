package com.production.management.controller.requirements_planning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.production.management.service.requirements_planning.RequirementsPlanningService;

@Controller
@RequestMapping("/requirements-planning")
public class RequirementsPlanningController {
	
	@Autowired
	private RequirementsPlanningService requirementsPlanningService;
	
	public String calculateRequirementsPlanning() {
		
		
		
		return "/requirements-planning/requirements_planning";
	}
}
