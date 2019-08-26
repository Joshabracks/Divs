package com.josh.divs.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;
import com.josh.divs.tools.ColorGenerator;
import com.josh.divs.tools.FirstMood;

@Service
public class DivService {
	
	DivRepository divs;
	ColorGenerator colors;
	Random rando;
	FirstMood mood;
	
	public DivService(DivRepository divs) {
		this.divs = divs;
		ColorGenerator col = new ColorGenerator();
		this.colors = col;
		Random ran = new Random();
		this.rando = ran;
		FirstMood moo = new FirstMood();
		this.mood = moo;
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
		newDiv.setRadius(rando.nextInt(25));
		newDiv.setMood(mood.get());
		
		divs.save(newDiv);
		return newDiv;
		
	}
}
