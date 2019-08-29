package com.josh.divs.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.josh.divs.models.Div;
import com.josh.divs.services.DivService;
import com.josh.divs.tools.JsUpdateString;
import com.josh.divs.tools.NameGenerator;

@Controller
public class DivsController {
	
	public final DivService divs;
	
	public DivsController(DivService divs) {
		this.divs = divs;
	}

	@RequestMapping("/")
	public String index(Model model) {
		List<Div> allDivs = divs.allDivs();
		model.addAttribute("allDivs", allDivs);
		return "/index.jsp";
		}
	
	@RequestMapping(value="/spawn", method=RequestMethod.POST)
	public ResponseEntity<?> spawnAjax() {
		System.out.println("**************************************");
		System.out.println("SPAWNING");
		NameGenerator name = new NameGenerator();
		Div newDiv = divs.createDiv(name.name());
		JsUpdateString update = new JsUpdateString(divs);
		String result = update.getData(newDiv);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<?> updateAjax(){
		JsUpdateString update = new JsUpdateString(divs);
		String result = update.getData();
		return ResponseEntity.ok(result);
	}
	

	
}