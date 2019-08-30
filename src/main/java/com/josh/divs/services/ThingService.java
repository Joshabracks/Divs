package com.josh.divs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josh.divs.models.Thing;
import com.josh.divs.repositories.ThingRepository;

@Service
public class ThingService {
	private ThingRepository things;
	public ThingService(ThingRepository things) {
		this.things = things;
	}
	
	public List<Thing> allThings() {
		List<Thing> allThings = (List<Thing>) things.findAll();
		return allThings;
	}
	
	//create a new Thing
	public Thing createThing(String name) {
		Thing newThing = new Thing();
		newThing.setName(name);
		things.save(newThing);
		return newThing;
		
	}
}
