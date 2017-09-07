package org.kulig.renewableenergy.controller;

import org.kulig.renewableenergy.model.entities.PvModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/PvModule")
public class PvModuleController {
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addPvModule(Model model){
		model.addAttribute("pvModule", new PvModule());
		return "PvModuleForm";
	}

}
