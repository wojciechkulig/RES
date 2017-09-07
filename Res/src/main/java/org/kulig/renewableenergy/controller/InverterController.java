package org.kulig.renewableenergy.controller;

import org.kulig.renewableenergy.model.entities.Inverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Inverter")
public class InverterController {
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addInverter(Model model){
		model.addAttribute("inverter",new Inverter());
		return "InverterForm";
	}

}
