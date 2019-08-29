package com.josh.divs.schedules;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;
import com.josh.divs.routines.FriendlyPredicate;
import com.josh.divs.routines.Predicate;
import com.josh.divs.routines.WigglingPredicate;
import com.josh.divs.services.DivService;
import com.josh.divs.services.ThingService;
import com.josh.divs.tools.FirstTrait;
import com.josh.divs.tools.NameGenerator;


@Component
public class ScheduledTasks {
    
	DivService divs;
	DivRepository repo;
	NameGenerator names;
	ThingService things;
	public ScheduledTasks(DivService divs, DivRepository repo, ThingService things){
		this.divs = divs;
		NameGenerator nam = new NameGenerator();
		this.names = nam;
		this.repo = repo;
		this.things = things;
	}
	
	@Scheduled(fixedRate = 100)
    public void move() {
		List<Div> allDivs = divs.allDivs();
		for (int i = 0; i < allDivs.size(); i++) {
		Div current = allDivs.get(i);
		if (current.getX() < current.getTargetX()) {
			int x = current.getX() + 20;
			current.setX(x);
			repo.save(current);
		}
		if (current.getX() > current.getTargetX()) {
			int x = current.getX() - 20;
			current.setX(x);
			repo.save(current);
		}
		if (current.getY() > current.getTargetY()) {
			int x = current.getY() - 20;
			current.setY(x);
			repo.save(current);
		}
		if (current.getY() < current.getTargetY()) {
			int x = current.getY() + 20;
			current.setY(x);
			repo.save(current);
		}
		
		}
	}
    @Scheduled(fixedRate = 750)
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
    	List<Div> allDivs = divs.allDivs();
    	for (int i = 0; i < allDivs.size(); i++) {
    		Div current = allDivs.get(i);
    		if (current.getStatus().equals("idle")) {
    			
	    		if (current.getTrait().equals("friendly")) {
	    			FriendlyPredicate friendly = (FriendlyPredicate) new FriendlyPredicate(repo);
	    			friendly.call(current);
	    		}
	    		else if (current.getTrait() == "antisocial") {
	    			//MAKE SPACE
	    		}
	    		else if (current.getTrait() == "fighter") {
	    			//FIND ENEMY or SOCIALIZE 
	    		}
	    		else {
	    			FirstTrait firstTrait = new FirstTrait();
	    			String trait = firstTrait.get();
	    			current.setTrait(trait);
	    			repo.save(current);
	    			
	    		}
    		}
    		Predicate wiggle = new WigglingPredicate(repo);
    		wiggle.call(current, allDivs);
    		
    	}
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
