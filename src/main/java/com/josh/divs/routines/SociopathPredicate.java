package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;

@Service
public class SociopathPredicate {
	
		Random rando;
		ActionTools tools;
		
		public SociopathPredicate() {
			Random ran = new Random();
			this.rando = ran;
			ActionTools too = new ActionTools();
			this.tools = too;
		
	}
		
		public Divvy call(Divvy self, List<Divvy> allDivvy) {
			if (self.status == null) {
				self.status = "idle";
			}
			if (self.status.equals("stalking")) {
				Divvy target = allDivvy.get(0);
				boolean allClear = false;
				for (int i = 0; i < allDivvy.size(); i++) {
					if (self.targetId == allDivvy.get(i).id) {
						target = allDivvy.get(i);
					}
				}
				for (int i = 0; i < allDivvy.size(); i++) {
					if ((allDivvy.get(i).id != self.id) && ((tools.xProx(allDivvy.get(i), target) > 300) && (tools.yProx(allDivvy.get(i), target) > 300))) {
						allClear = true;
					}
					else {
						allClear = false;
					}
				}
				if ((allClear == true) && ((tools.xProx(self, target) < 75) && tools.yProx(self,  target) < 75)) {
					self.mood = tools.meanFace();
					self.targetX = target.x;
					self.targetY = target.y;
					target.age = target.age - 10;
					if (target.trait.equals("fighter")) {
						self.age = self.age - 15;
					}
					self.status = "attacking";
					self.action = "is attacking " + target.name;
					//KEEP BELOW CODE FOR WHEN VICTIM PREDICATE AND TRAIT ARE ADDED
//					else {	
//					(target.trait = victim);
//					}
				}
			}
			else if (self.friends.size() > 0) {
				Long mark = self.friends.get(rando.nextInt(self.friends.size()));
				Divvy target = allDivvy.get(0);
				for (int i = 0; i < allDivvy.size(); i++) {
					if (mark == allDivvy.get(i).id) {
						target = allDivvy.get(i);
					}
				}
				self.targetId = target.id;
				self.status = "stalking";
				self.action = "wants to talk to " + target.name;
				self.love = target.name;
				self.hate = target.name;
				self.mood = tools.friendlyMood();
				self.targetX = target.x;
				self.targetY = target.y;
			}
			else if (self.status.equals("idle")) {
				
				Divvy target = allDivvy.get(rando.nextInt(allDivvy.size()));
				if (target.status == null) {
					target.status = "idle";
				}
				
				if (target.status.equals("idle")) {
					self.status = "friendlyApproach";
					self.targetY = target.y;
					self.targetX = target.x;
					self.targetId = target.id;
					self.mood = tools.friendlyMood();
					self.action = " wants to talk to " + target.name;
					return self;
				}
			}
			else if (self.status.equals("friendlyApproach")) {
				
				self.mood = tools.friendlyMood();
				Divvy target = allDivvy.get(0);
				int check = 0;
				while ((target.id != self.targetId) && (check < allDivvy.size())) {
					
					check ++;
					target = allDivvy.get(check);
					
					
				}
				
				if ((tools.xProx(self, target) < 75) && (tools.yProx(self, target) < 75)) {
					self.status = "chatting";
					self.action = "Chatting with " + target.name;
					target.targetId = self.id;
					self.targetX = self.x;
					self.targetY = self.y;
					target.targetX = target.x;
					target.targetY = target.y;
					}
				return self;
			}
				
				
			
			else if (self.status.equals("chatting")) {
				Divvy target = allDivvy.get(0);
				int check = 0;
				while ((target.id != self.targetId) && (check < allDivvy.size())) {
					target = allDivvy.get(check);
					check ++;
				}
					Integer chatScore = tools.compareInterests(self,  target);
					
					if (target.trait.equals("friendly")){
						chatScore++;
						
						
					if (chatScore > 0) {
						List<Long> friends = self.friends;
						boolean isFriend = false;
						for (int i = 0; i < friends.size(); i++) {
							if (friends.get(i) == target.id) {
								isFriend = true;
								
							}
						}
						if (isFriend != true) {
							List<Long> enemies = self.enemies;
							for (int i = 0; i < enemies.size(); i++) {
								if (enemies.get(i) == target.id) {
									enemies.remove(i);
									self.enemies = enemies;
								}
							}
							friends.add(target.id);
							target.friends.add(self.id);
							self.friends = friends;
							self.action = "and " +  target.name +  " are friends!";
							self.mood = "<p style='color: pink;'>^_^</p>";
						}
					}
					if (chatScore < 0) {
						self.mood = "T_T";
						List<Long> friends = self.friends;
						
						for (int i = 0; i < friends.size(); i++) {
							if (friends.get(i) == target.id) {
								friends.remove(i);
								self.friends = friends;
								
							}
						}
						List<Long> enemies = self.enemies;
						boolean isEnemy = false;
						for (int i = 0; i < enemies.size(); i++) {
							if (enemies.get(i) == target.id) {
								isEnemy = true;
							}
						}
						if (isEnemy != true) {
							enemies.add(target.id);
							self.enemies = enemies;
						}
					}
					
				}
				self.status = "stillChatting";
				
			}
			else if (self.status.equals("stillChatting")) {
				self.status = "idle";
				Divvy target = allDivvy.get(0);
				int check = 0;
				while ((target.id != self.targetId) && (check < allDivvy.size())) {
					target = allDivvy.get(check);
					check ++;
				}
				target.status = "idle";
				
			}
			return self;
		}
}
