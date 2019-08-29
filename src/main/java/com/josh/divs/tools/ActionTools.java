package com.josh.divs.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;

public class ActionTools {
	
	public ActionTools(){}
	
	public Integer xProx(Divvy divA, Divvy divB) {
		return Math.abs(divA.x - divB.x);
	}
	
	public Integer yProx(Divvy divA, Divvy divB) {
		return Math.abs(divA.x - divB.x);
	}
	
	public String friendlyMood() {
		List<String> list = Arrays.asList(new String[]{"^_^", "^.^"});
		Random r = new Random();
		String mood = list.get(r.nextInt(list.size()));
		return mood;
	}
	
	public Integer compareInterests(Divvy divA, Divvy divB) {
		Integer score = 0;
		if (divA.love ==  divB.love) {
			score = score + 5;
		}
		if (divA.like == divB.love) {
			score = score + 3;
		}
		if (divA.love == divB.like) {
			score = score + 3;
		}
		if (divA.like == divB.like) {
			score = score + 2;
		}
		if (divA.dislike == divB.dislike) {
			score = score + 1;
		}
		if (divA.hate == divB.dislike) {
			score = score + 1;
		}
		if (divA.dislike == divB.hate) {
			score = score + 1;
		}
		if (divA.hate == divB.hate) {
			score = score + 4;
		}
		if (divA.hate == divB.like) {
			score = score - 3;
		}
		if (divA.like == divB.hate) {
			score = score - 3;
		}
		if (divA.hate == divB.love) {
			score = score - 6;
		}
		if (divA.love == divB.hate) {
			score = score - 6;
		}
		if (divA.love == divB.dislike) {
			score = score - 2;
		}
		if (divA.dislike == divB.love) {
			score = score - 2;
		}
		if (divA.dislike == divB.like) {
			score = score - 1;
		}
		if (divA.like == divB.dislike) {
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
		div.setLove(divvy.love);
		div.setEnjoy(divvy.like);
		div.setDislike(divvy.dislike);
		div.setHate(divvy.hate);
		div.setTargetId(divvy.targetId);
		repo.save(div);
	}
}
