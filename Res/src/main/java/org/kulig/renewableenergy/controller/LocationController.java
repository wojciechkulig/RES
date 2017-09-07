package org.kulig.renewableenergy.controller;

import org.kulig.renewableenergy.charts.IrradianceBarChart;
import org.kulig.renewableenergy.model.DTO.LocationDTO;
import org.kulig.renewableenergy.model.entities.City;
import org.kulig.renewableenergy.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LocationController {
	
	@Autowired
	private CityService cityService;
	

	@RequestMapping(path="/",method= RequestMethod.GET)
	public String getLocationPage(Model model){	
		model.addAttribute("locationDTO", new LocationDTO());

		return "lokalizacja";
	}
	
	@RequestMapping(path="/",method= RequestMethod.POST)
	public String getLocationWithMeteorologicalData(@ModelAttribute("locationDTO") LocationDTO locationDTO, Model model){ 
		City city = cityService.setCityAndWeatherConditionsForLocation(locationDTO);
		String url = new IrradianceBarChart().drawMonthlyIrradianceBarChart(cityService.getCityMonthlyIrradiance(city));
		model.addAttribute("pieUrl",url);
		model.addAttribute("locationDTO", locationDTO);
		model.addAttribute("city", city);
		return "lokalizacja";
	}
	
	

}
