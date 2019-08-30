package com.josh.divs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;
import com.josh.divs.routines.FriendlyPredicate;
import com.josh.divs.routines.Predicate;
import com.josh.divs.routines.WigglingPredicate;
import com.josh.divs.services.DivService;
import com.josh.divs.services.ThingService;
import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;
import com.josh.divs.tools.FirstTrait;
import com.josh.divs.tools.JsUpdateString;
import com.josh.divs.tools.NameGenerator;

@Controller
public class DivsController {
	
	
	DivService divs;
	DivRepository repo;
	NameGenerator names;
	ThingService things;
	List<Divvy> allDivvy;
	ActionTools tools;
	
	
	public DivsController(DivService divs, DivRepository repo, ThingService things) {
		this.divs = divs;
		NameGenerator nam = new NameGenerator();
		this.names = nam;
		this.repo = repo;
		this.things = things;
		ActionTools too = new ActionTools();
		this.tools = too;
		this.allDivvy = initialLoad(divs.allDivs());
	}

	@RequestMapping("/")
	public String index(Model model) {
		List<Div> allDivs = divs.allDivs();
		model.addAttribute("allDivs", allDivs);
		return "/index.jsp";
		}
	
	@RequestMapping(value="/spawn", method=RequestMethod.POST)
	public ResponseEntity<?> spawnAjax() {
		save();
		NameGenerator name = new NameGenerator();
		Div newDiv = divs.createDiv(name.name());
		load();
		JsUpdateString update = new JsUpdateString();
		String result = update.getData(newDiv);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseEntity<?> updateAjax(){
		JsUpdateString update = new JsUpdateString();
		String result = update.getData(this.allDivvy);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}
	
	//SCHEDULED TASKS
	
	private void load() {
		List<Div> allDivs = divs.allDivs();
		List<Divvy> divList = new ArrayList<>();
		for (int i = 0; i < allDivs.size(); i++) {
			Divvy newDivvy = new Divvy(allDivs.get(i));
			divList.add(newDivvy);
		}
		this.allDivvy = divList;
	}
	

	private List<Divvy> initialLoad(List<Div> allDivs){
		List<Divvy> divvies = new ArrayList<Divvy>();
		for (int i = 0; i < allDivs.size(); i++) {
			Divvy newDivvy = new Divvy(allDivs.get(i));
			divvies.add(newDivvy);
		}
		return divvies;
	}
	
	@Scheduled(fixedRate = 6000)
	public void save() {
			for (int i = 0; i < this.allDivvy.size(); i++) {
				tools.saveDiv(this.allDivvy.get(i), repo); 
		}
			load();
	}
	
	
	@Scheduled(fixedRate = 20)
    public void move() {
		for (int i = 0; i < this.allDivvy.size(); i++) {
		Divvy current = allDivvy.get(i);
		if (current.targetY == null) {
			current.targetY = current.y;
		}
		if (current.targetX == null) {
			current.targetX = current.x;
		}
		if (current.x < current.targetX) {
			int x = current.x + 1;
			current.x = x;
		}
		if (current.x > current.targetX) {
			int x = current.x - 1;
			current.x = x;
		}
		if (current.y > current.targetY) {
			int x = current.y - 1;
			current.y = x;
		}
		if (current.y < current.targetY) {
			int x = current.y + 1;
			current.y = x;
		}
		allDivvy.set(i, current);
		
		}
	}
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
    	
    	if (things.allThings().size() < 3) {
    		things.createThing("cats");
    		things.createThing("cake");
    		things.createThing("pie");
    		things.createThing("cars");
    		things.createThing("trains");
    		things.createThing("dogs");
    		things.createThing("music");
    		things.createThing("food");
    		
    	}
    	if (divs.allDivs() == null) {
    		divs.createDiv(names.name());
    	}
    	if (divs.allDivs().size() < 4) {
    		divs.createDiv(names.name());
    	}
    	
    	for (int i = 0; i < this.allDivvy.size(); i++) {
    		Divvy current = this.allDivvy.get(i);
    		if (current.status == null) {
    			current.status = "idle";
    		}
    		if (current.status.equals("idle")) {
    			
	    		if (current.trait.equals("friendly")) {
	    			FriendlyPredicate friendly = (FriendlyPredicate) new FriendlyPredicate();
	    			this.allDivvy = friendly.call(current, this.allDivvy);
	    		}
	    		else if (current.trait.equals("antisocial")) {
	    			//MAKE SPACE
	    		}
	    		else if (current.trait.equals("fighter")) {
	    			//FIND ENEMY or SOCIALIZE 
	    		}
	    		else {
	    			FirstTrait firstTrait = new FirstTrait();
	    			String trait = firstTrait.get();
	    			current.trait = trait;
	    			
	    		}
    		}
    		Predicate wiggle = new WigglingPredicate();
    		current = wiggle.call(current, this.allDivvy);
    		this.allDivvy.set(i, current);
    		
    	}
    }


	
}