package com.josh.divs.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.josh.divs.models.Div;

public class Divvy {
	public Long id;
	public Long age;
	public String name;
	public String trait;
	public Integer radius;
	public String color;
	public String outline;
	public String mood;
	public String status;
	public Integer x;
	public Integer y;
	public Integer targetX;
	public Integer targetY;
	public List<Long> friends;
	public List<Long> enemies;
	public String love;
	public String like;
	public String dislike;
	public String hate;
	public Long targetId;
	public String action;
	public String lastAction;
	public Integer size;
	
	public Divvy(Div div) {
		this.id = div.getId();
		this.name = div.getName();
		this.trait = div.getTrait();
		this.radius = div.getRadius();
		this.color = div.getColor();
		this.outline = div.getOutline();
		this.mood = div.getMood();
		this.status = "idle";
		this.x = div.getX();
		this.y = div.getY();
		this.targetX = div.getTargetX();
		this.targetY = div.getTargetY();
		this.love = div.getLove();
		this.like = div.getEnjoy();
		this.dislike = div.getDislike();
		this.hate = div.getHate();
		this.targetId = div.getTargetId();
		this.action = "Is chillin'";
		this.lastAction = "idle";
		this.friends = new ArrayList<>();
		this.enemies = new ArrayList<>();
		Random r = new Random();
		this.age = (long) r.nextInt(2000);
		this.size = 50;
	}
}
