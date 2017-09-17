package org.kulig.renewableenergy.controller;

import org.kulig.renewableenergy.model.DTO.EconomicAnalysisData;
import org.kulig.renewableenergy.model.entities.EconomicAnalysisResults;
import org.kulig.renewableenergy.service.EconomicAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/analizaEkonomiczna")
public class EconomicAnalysisController {
	
	@Autowired
	private EconomicAnalysisService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getEconomicAnalysisPage(Model model){
		model.addAttribute("economicAnalysisData" ,new EconomicAnalysisData());
		model.addAttribute("tariffGroupName",service.getTariffGroupName());
		return "economicAnalysis";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String getEconomicAnalysisPageWithResults(@ModelAttribute("economicAnalysisData") EconomicAnalysisData economicAnalysisData, Model model){
		service.updateEconomicAnalysisBean(economicAnalysisData);
		model.addAttribute("economicAnalysisData", economicAnalysisData);
		EconomicAnalysisResults economicResults = service.getEconomicAnalysisResults();
		model.addAttribute("results", economicResults);
		model.addAttribute("chart",service.getCumulativeCashFlowChartUrl(economicResults.getDiscountedCumulativeCashFlow()));
		return "economicAnalysis";
	}

}
