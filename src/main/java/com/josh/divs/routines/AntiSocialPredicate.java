package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;

@Service
public class AntiSocialPredicate {
	Random rando;
	ActionTools tools;
	
	public AntiSocialPredicate() {
		Random ran = new Random();
		this.rando = ran;
		ActionTools too = new ActionTools();
		this.tools = too;
	}
	
	public Divvy call(Divvy self, List<Divvy> allDivvy) {
		boolean avoiding = false;
		if (self.status == null) {
			self.status = "idle";
		}
		for (int i = 0; i < allDivvy.size(); i++) {
			if (allDivvy.get(i) != self) {
				if ((tools.xProx(self, allDivvy.get(i)) < 200) && (tools.yProx(self, allDivvy.get(i)) < 200)) {
				
					avoiding = true;
					self.status = "avoiding";
					Divvy target = allDivvy.get(i);
					self.targetId = target.id;
					self.action = "avoiding " + target.name;
					self.mood = tools.scaredFace();
					if (self.x > target.x) {
						self.targetX = self.x + 300;
					}
					if (self.x < target.x) {
						self.targetX = self.x - 300;
					}
					if (self.y > target.y) {
						self.targetY = self.y + 300;
					}
					if (self.y < target.y) {
						self.targetY = self.y - 300;
					}
				}
			}
			
		}
		if (avoiding == true) {
			Predicate wiggle = new WigglingPredicate();
			self = wiggle.call(self, allDivvy);
		}
		return self;
	}

}
