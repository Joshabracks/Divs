package com.josh.divs.routines;

import java.util.List;
import java.util.Random; 

import org.springframework.stereotype.Service;

import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;

@Service
public class FighterPredicate {
	private Random rando;
	private ActionTools tools;
	
	public FighterPredicate() {
		Random ran = new Random();
		this.rando = ran;
		ActionTools too = new ActionTools();
		this.tools = too;
	}
	
	public Divvy call(Divvy self, List<Divvy> allDivvy) {
		boolean chasing = false;
		if (self.status == null) {
			self.status = "idle";
		}
		Integer aggro = rando.nextInt(10);
		for (int i = 0; i < allDivvy.size(); i++) {
			if (allDivvy.get(i) != self) {
				if ((tools.xProx(self, allDivvy.get(i)) < 200) && (tools.yProx(self, allDivvy.get(i)) < 200)) {
					Divvy target = allDivvy.get(i);
					chasing = true;
					if (aggro > 8) {
						self.targetX = target.x;
						self.targetY = target.y;
						self.status = "chasing";
						self.action = "is chasing " + target.name;
						self.mood = "<p style=' color: red;'>+_+</p>"; 
					} else {
						self.targetId = target.id;
						self.action = "is angry with " + target.name;
						self.mood = tools.meanFace();
						self.targetX = self.x + rando.nextInt(100)-50;
						self.targetY = self.y + rando.nextInt(100)-50;
					}
				}
			}
		}
		if (chasing == false) {
			Predicate wiggle = new WigglingPredicate();
			self = wiggle.call(self, allDivvy);
		}
		return self;
	}
}
