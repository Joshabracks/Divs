package com.josh.divs.schedules;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;
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
	
    
    @Scheduled(fixedRate = 60000)
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
    		if (current.getStatus() == "idle") {
	    		if (current.getTrait() == "friendly") {
	    			//SOCIALIZE
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
	    			System.out.println(current.getName() + ": " + current.getTrait());
	    		}
    		}
    		
    	}
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
