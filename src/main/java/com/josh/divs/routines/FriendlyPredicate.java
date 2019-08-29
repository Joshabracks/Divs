package com.josh.divs.routines;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;
import com.josh.divs.tools.ActionTools;

@Service
public class FriendlyPredicate{
	
	DivRepository divs;
	Random rando;
	ActionTools tools;
	
	public FriendlyPredicate(DivRepository divs) {
		this.divs = divs;
		Random ran = new Random();
		this.rando = ran;
		ActionTools too = new ActionTools();
		this.tools = too;
	}
	
	public void call(Div self) {
		if (self.getStatus().equals("idle")) {
			List<Div> allDivs = (List<Div>) divs.findAll();
			Div target = allDivs.get(rando.nextInt(allDivs.size()));
			if (target.getStatus().equals("idle")) {
				self.setStatus("friendlyApproach");
				self.setTargetY(target.getY());
				self.setTargetX(target.getX());
				self.setTargetId(target.getId());
				self.setMood(tools.friendlyMood());
				divs.save(self);
				divs.save(target);
			}
		}
		else if (self.getStatus().equals("friendlyApproach")) {
			self.setMood(tools.friendlyMood());
			Optional<Div> option = divs.findById(self.getTargetId());
			if (option.isPresent()) {
				Div target = option.get();
				if ((tools.xProx(self, target) < 75) && (tools.yProx(self, target) < 75)) {
					self.setStatus("chatting");
					target.setStatus("chatting");
					target.setTargetId(self.getId());
					self.setTargetX(self.getX());
					self.setTargetY(self.getY());
					target.setTargetX(target.getX());
					target.setTargetY(target.getY());
					divs.save(self);
					divs.save(target);
				}
			}
			
			
		}
		else if (self.getStatus().equals("chatting")) {
			Optional<Div> option = divs.findById(self.getTargetId());
			if (option.isPresent()) {
				Div target = option.get();
				Integer chatScore = tools.compareInterests(self,  target);
				if (target.getTrait().equals("friendly")){
					chatScore++;
				}
				if (chatScore > 0) {
					List<Long> friends = self.getFriends();
					boolean isFriend = false;
					for (int i = 0; i < friends.size(); i++) {
						if (friends.get(i) == target.getId()) {
							isFriend = true;
						}
					}
					if (isFriend != true) {
						List<Long> enemies = self.getEnemies();
						for (int i = 0; i < enemies.size(); i++) {
							if (enemies.get(i) == target.getId()) {
								enemies.remove(i);
								self.setEnemies(enemies);
							}
						}
						friends.add(target.getId());
						self.setFriends(friends);
						divs.save(self);
					}
				}
				if (chatScore < 0) {
					self.setMood("T_T");
					List<Long> friends = self.getFriends();
					
					for (int i = 0; i < friends.size(); i++) {
						if (friends.get(i) == target.getId()) {
							friends.remove(i);
							self.setFriends(friends);
							
						}
					}
					List<Long> enemies = self.getEnemies();
					boolean isEnemy = false;
					for (int i = 0; i < enemies.size(); i++) {
						if (enemies.get(i) == target.getId()) {
							isEnemy = true;
						}
					}
					if (isEnemy != true) {
						enemies.add(target.getId());
						self.setEnemies(enemies);
						divs.save(self);
					}
				}
				
			}
			self.setStatus("stillChatting");
			divs.findById(self.getTargetId()).get().setStatus("stillChatting");
			divs.save(self);
			divs.save(divs.findById(self.getTargetId()).get());
			
		}
		else if (self.getStatus().equals("stillChatting")) {
			self.setStatus("idle");
			divs.save(self);
			Div target = divs.findById(self.getTargetId()).get();
			target.setStatus("idle");
			divs.save(target);
		}
	}

	

}
