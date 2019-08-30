package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;
import com.josh.divs.tools.NameGenerator;

@Service
public class TreePredicate {
	Random rando;
	ActionTools tools;
	
	public TreePredicate() {
		Random ran = new Random();
		this.rando = ran;
		ActionTools too = new ActionTools();
		this.tools = too;
	}
	
	private Divvy berry(Divvy self, List<Divvy> allDivvy) {
		for (int i = 0; i < allDivvy.size(); i++) {
			if (allDivvy.get(i) != self) {
				Divvy current = allDivvy.get(i);
				if ( (tools.xProx(self,  current) < 60) && (tools.yProx(self,  current) < 60) ) {
					if (self.action.equals("has berries!")) {
						return self;
					}
					Integer comp = tools.compareInterests(self, current);
					if (comp > 0) {
						current.status = "berry";
						current.x = rando.nextInt(50) + self.x - 25;
						current.y = rando.nextInt(50) + self.y - 25;
						current.targetX = current.x;
						current.targetY = current.y;
						current.size = 10;
						current.radius = 5;
						current.mood = "";
						self.status = "tree";
						self.action = "has berries!";
					}
				}
			}
		}
		return self;
	}
	
	private Divvy berry2(Divvy self, List<Divvy> allDivvy) {
		for (int i = 0; i < allDivvy.size(); i++) {
			if (allDivvy.get(i) != self) {
				Divvy current = allDivvy.get(i);
				if ( (tools.xProx(self,  current) < 60) && (tools.yProx(self,  current) < 60) ) {
					if (self.action.equals("has 2 berries!")) {
						return self;
					}
					Integer comp = tools.compareInterests(self, current);
					if (comp > 0) {
						current.status = "berry";
						current.x = rando.nextInt(50) + self.x - 25;
						current.y = rando.nextInt(50) + self.y - 25;
						current.targetX = current.x;
						current.targetY = current.y;
						current.size = 10;
						current.radius = 5;
						current.mood = "";
						self.status = "tree";
						self.action = "has 2 berries!";
					}
				}
			}
		}
		return self;
	}
	
	private Divvy berry3(Divvy self, List<Divvy> allDivvy) {
		for (int i = 0; i < allDivvy.size(); i++) {
			if (allDivvy.get(i) != self) {
				Divvy current = allDivvy.get(i);
				if ( (tools.xProx(self,  current) < 60) && (tools.yProx(self,  current) < 60) ) {
					if (self.action.equals("has 3 berries!")) {
						return self;
					}
					Integer comp = tools.compareInterests(self, current);
					if (comp > 0) {
						current.status = "berry";
						current.x = rando.nextInt(50) + self.x - 25;
						current.y = rando.nextInt(50) + self.y - 25;
						current.targetX = current.x;
						current.targetY = current.y;
						current.size = 10;
						current.radius = 5;
						current.mood = "";
						self.status = "tree";
						self.action = "has 3 berries!";
					}
				}
			}
		}
		return self;
	}
	
	private void hatchBerries(Divvy self, List<Divvy> allDivvy) {
		for (int i = 0; i < allDivvy.size(); i++) {
			if (allDivvy.get(i).status.equals("berry")) {
				
				Divvy current = allDivvy.get(i);
				
				if ((tools.xProx(self, current) < 75) && (tools.yProx(self, current) < 75)) {
					current.age = (long) 0;
					NameGenerator babyName = new NameGenerator();
					current.name = babyName.name(self.name, current.name);
					current.size = 50;
					current.radius = rando.nextInt(25);
					current.status = "idle";
					current.action = "is born!";
				}
			}
		}
	}
	
	
	public Divvy call(Divvy self, List<Divvy> allDivvy) {
		self.status = "tree";
		self.targetX = self.x;
		self.targetY = self.y;
		self = berry(self, allDivvy);
		if (self.action.equals("has berries!")) {
			self = berry2(self, allDivvy);
			if (self.action.equals("has 2 berries!")) {
				self = berry3(self, allDivvy);
				if (self.action.equals("has 3 berries!")) {
					self.age = (long) 0;
					NameGenerator babyName = new NameGenerator();
					self.name = babyName.name(self.name);
					self.size = 50;
					self.radius = rando.nextInt(25);
					self.status = "idle";
					self.action = "is born!";
					hatchBerries(self, allDivvy);
				}
			}
		}
		
		
			
		return self;
		}
}
