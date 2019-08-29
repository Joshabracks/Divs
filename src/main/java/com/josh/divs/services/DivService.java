package com.josh.divs.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.models.Div;
import com.josh.divs.models.Thing;
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
	ThingService things;
	
	public DivService(DivRepository divs, ThingService things) {
		this.divs = divs;
		ColorGenerator col = new ColorGenerator();
		this.colors = col;
		Random ran = new Random();
		this.rando = ran;
		FirstMood moo = new FirstMood();
		this.mood = moo;
		FirstTrait tra = new FirstTrait();
		this.trait = tra;
		this.things = things;
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
		newDiv.setX(rando.nextInt(500));
		newDiv.setY(rando.nextInt(500));
		List<Thing> allThings = things.allThings();
		newDiv.setEnjoy(allThings.get(rando.nextInt(allThings.size())).getName());
		String next = allThings.get(rando.nextInt(allThings.size())).getName();
		while(next == newDiv.getEnjoy()) {
			next = allThings.get(rando.nextInt(allThings.size())).getName();
		}
		newDiv.setLove(next);
		while((next == newDiv.getEnjoy()) || (next == newDiv.getLove())) {
			next = allThings.get(rando.nextInt(allThings.size())).getName();
		}
		newDiv.setDislike(next);
		while((next == newDiv.getEnjoy()) || (next == newDiv.getLove()) || (next == newDiv.getDislike())) {
			next = allThings.get(rando.nextInt(allThings.size())).getName();
		}
		newDiv.setHate(next);
		divs.save(newDiv);
		return newDiv;
		
	}
}
