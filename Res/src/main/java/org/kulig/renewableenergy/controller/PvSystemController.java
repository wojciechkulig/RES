package org.kulig.renewableenergy.controller;

import java.util.List;

import org.kulig.renewableenergy.model.DTO.PvSystemConfigurationDTO;
import org.kulig.renewableenergy.model.entities.PvSystemEnergyBilance;
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
		service.updatePvModuleBean(pvSystemConfigurationDTO.getPvModule());
		service.updateTariffGroupBean(pvSystemConfigurationDTO.getTariffGroup(), pvSystemConfigurationDTO.getAnnualEnergyConsumption());
		List<List<PvSystemEnergyBilance>> lifeTimeEnergyBilance = service.calculateLifeTimeEnergyBilance(pvSystemConfigurationDTO);
		service.updateEnergyBilance(lifeTimeEnergyBilance);
		model.addAttribute("chartUrl",service.createEnergyBilanceBarChart(lifeTimeEnergyBilance));
		model.addAttribute("pvSystemConfigurationDTO", pvSystemConfigurationDTO);
		model.addAttribute("tariffGroups", service.getTariffGroups());
		model.addAttribute("pvModules", service.getPvModules());
		return "PvSystemConfiguration";

	}

}
