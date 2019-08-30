package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;

@Service
public class AntiSocialPredicate {
	private Random rando;
	private ActionTools tools;
	
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
		boolean personal = rando.nextBoolean();
		if (self.status.equals("seekingFriend")) {
			Divvy target = allDivvy.get(0);
			int check = 0;
			while ((target.id != self.targetId) && (check < allDivvy.size())) {
				check ++;
				target = allDivvy.get(check);
			}
			if ((tools.xProx(self, target)) < 75 && (tools.yProx(self,  target) < 75)) {
				self.status.equals("idle");
				self.action = "feeling better now that " + target.name + "is near";
			} else {
				self.targetX = target.x;
				self.targetY = target.y;
				self.status = "seekingFriend";
				self.action = "Still trying to get to " + target.name + " because everyone else is scary.";
				self.targetId = target.id;
				avoiding = true;
			}
		}
		if (personal) {
			for (int i = 0; i < allDivvy.size(); i++) {
				if (allDivvy.get(i) != self) {
					if ((tools.xProx(self, allDivvy.get(i)) < 75) && (tools.yProx(self, allDivvy.get(i)) < 75)) {
						if (allDivvy.get(i).trait.equals("fighter")) {
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
			}
		} else {
			for (int i = 0; i < allDivvy.size(); i++) {
				if (allDivvy.get(i).friends.size() > 0) {
					for (int j = 0; j < allDivvy.get(i).friends.size(); j++) {
						if (allDivvy.get(i).friends.get(j).equals(self.id)) {
							System.out.println("inside");
							self.targetX = allDivvy.get(i).x;
							self.targetY = allDivvy.get(i).y;
							self.status = "seekingFriend";
							self.action = "Trying to find comfort with " + allDivvy.get(i).name;
							self.targetId = allDivvy.get(i).id;
							avoiding = true;
						}
					}
				}
			}
		}
		if (avoiding == false) {
			Predicate wiggle = new WigglingPredicate();
			self = wiggle.call(self, allDivvy);
		}
		return self;
	}
}
