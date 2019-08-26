package com.josh.divs.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.josh.divs.services.DivService;

@RestController
public class AiController {

	DivService divs;
	
	public AiController(DivService divs) {
		this.divs = divs;
	}
	
	
}
