package com.josh.divs.routines;


import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;

@Service
public class FriendlyPredicate{
	Random rando;
	ActionTools tools;
	
	public FriendlyPredicate() {
		Random ran = new Random();
		this.rando = ran;
		ActionTools too = new ActionTools();
		this.tools = too;
	}
	
	public Divvy call(Divvy self, List<Divvy> allDivvy) {
		if (self.status == null) {
			self.status = "idle";
		}
		if (self.status.equals("idle")) {
			
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
				self.action = self.name + " wants to talk to " + target.name;
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
					System.out.println(chatScore);
					
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
