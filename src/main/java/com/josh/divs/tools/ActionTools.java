package com.josh.divs.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;

public class ActionTools {
	
	public ActionTools(){}
	
	public Integer xProx(Div divA, Div divB) {
		return Math.abs(divA.getX() - divB.getX());
	}
	
	public Integer yProx(Div divA, Div divB) {
		return Math.abs(divA.getY() - divB.getY());
	}
	
	public String friendlyMood() {
		List<String> list = Arrays.asList(new String[]{"^_^", "^.^"});
		Random r = new Random();
		String mood = list.get(r.nextInt(list.size()));
		return mood;
	}
	
	public Integer compareInterests(Div divA, Div divB) {
		Integer score = 0;
		if (divA.getLove() ==  divB.getLove()) {
			score = score + 5;
		}
		if (divA.getEnjoy() == divB.getLove()) {
			score = score + 3;
		}
		if (divA.getLove() == divB.getEnjoy()) {
			score = score + 3;
		}
		if (divA.getEnjoy() == divB.getEnjoy()) {
			score = score + 2;
		}
		if (divA.getDislike() == divB.getDislike()) {
			score = score + 1;
		}
		if (divA.getHate() == divB.getDislike()) {
			score = score + 1;
		}
		if (divA.getDislike() == divB.getHate()) {
			score = score + 1;
		}
		if (divA.getHate() == divB.getHate()) {
			score = score + 4;
		}
		if (divA.getHate() == divB.getEnjoy()) {
			score = score - 3;
		}
		if (divA.getEnjoy() == divB.getHate()) {
			score = score - 3;
		}
		if (divA.getHate() == divB.getLove()) {
			score = score - 6;
		}
		if (divA.getLove() == divB.getHate()) {
			score = score - 6;
		}
		if (divA.getLove() == divB.getDislike()) {
			score = score - 2;
		}
		if (divA.getDislike() == divB.getLove()) {
			score = score - 2;
		}
		if (divA.getDislike() == divB.getEnjoy()) {
			score = score - 1;
		}
		if (divA.getEnjoy() == divB.getEnjoy()) {
			score = score -1;
		}
		return score;
	}
	
	public void saveDiv(Divvy divvy, DivRepository repo) {
		Div div = repo.findById(divvy.id).get();
		div.setId(divvy.id);
		div.setName(divvy.name);
		div.setTrait(divvy.trait);
		div.setRadius(divvy.radius);
		div.setColor(divvy.color);
		div.setOutline(divvy.outline);
		div.setMood(divvy.mood);
		div.setStatus(divvy.status);
		div.setX(divvy.x);
		div.setY(divvy.y);
		div.setTargetX(divvy.targetX);
		div.setTargetY(divvy.targetY);
		div.setFriends(divvy.friends);
		div.setLove(divvy.love);
		div.setEnjoy(divvy.like);
		div.setDislike(divvy.dislike);
		div.setHate(divvy.hate);
		div.setTargetId(divvy.targetId);
		repo.save(div);
	}
}
