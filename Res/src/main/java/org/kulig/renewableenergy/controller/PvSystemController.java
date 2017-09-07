package org.kulig.renewableenergy.controller;

import org.kulig.renewableenergy.model.DTO.PvSystemConfigurationDTO;
import org.kulig.renewableenergy.service.PvSystemConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/PvSystem")
public class PvSystemController {

	@Autowired
	private PvSystemConfigurationService service;

	@RequestMapping(method = RequestMethod.GET)
	public String getPvSystemConfigurationPage(Model model) {
		model.addAttribute("pvSystemConfigurationDTO", new PvSystemConfigurationDTO());
		model.addAttribute("tariffGroups", service.getTariffGroups());
		model.addAttribute("pvModules", service.getPvModules());
		return "PvSystemConfiguration";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String postPvSystemConfigurationPage(
			@ModelAttribute("pvSystemConfigurationDTO") PvSystemConfigurationDTO pvSystemConfigurationDTO,
			Model model) {
		System.out.println(pvSystemConfigurationDTO.getPvModule().getName());
		service.updatePvModuleBean(pvSystemConfigurationDTO.getPvModule());
		service.updateTariffGroupBean(pvSystemConfigurationDTO.getTariffGroup(), pvSystemConfigurationDTO.getAnnualEnergyConsumption());
		service.calculate();
		return "PvSystemConfiguration";

	}

}
