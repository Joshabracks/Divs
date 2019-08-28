package com.josh.divs.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;
import com.josh.divs.tools.ColorGenerator;
import com.josh.divs.tools.FirstMood;
import com.josh.divs.tools.FirstTrait;

@Service
public class DivService {
	
	DivRepository divs;
	ColorGenerator colors;
	Random rando;
	FirstMood mood;
	FirstTrait trait;
	
	public DivService(DivRepository divs) {
		this.divs = divs;
		ColorGenerator col = new ColorGenerator();
		this.colors = col;
		Random ran = new Random();
		this.rando = ran;
		FirstMood moo = new FirstMood();
		this.mood = moo;
		FirstTrait tra = new FirstTrait();
		this.trait = tra;
	}
	
	//get all divs
	public List<Div> allDivs() {
		List<Div> allDivs = (List<Div>) divs.findAll();
		return allDivs;
	}
	
	//create a new div
	public Div createDiv(String name) {
		Div newDiv = new Div();
		newDiv.setName(name);
		newDiv.setColor(colors.mix());
		newDiv.setOutline(colors.mix());
		newDiv.setMood(mood.get());
		newDiv.setRadius(rando.nextInt(25));
		newDiv.setTrait(trait.get());
		
		divs.save(newDiv);
		return newDiv;
		
	}
}
