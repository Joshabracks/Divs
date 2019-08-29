package com.josh.divs.tools;

import java.util.List;

import com.josh.divs.models.Div;

public class Divvy {
	Long id;
	String name;
	String trait;
	Integer radius;
	String color;
	String outline;
	String mood;
	String status;
	Integer x;
	Integer y;
	Integer targetX;
	Integer targetY;
	List<Long> friends;
	List<Long> enemies;
	String love;
	String like;
	String dislike;
	String hate;
	Long targetId;
	
	public Divvy(Div div){
		this.id = div.getId();
		this.name = div.getName();
		this.trait = div.getTrait();
		this.radius = div.getRadius();
		this.color = div.getColor();
		this.outline = div.getOutline();
		this.mood = div.getMood();
		this.status = div.getStatus();
		this.x = div.getX();
		this.y = div.getY();
		this.targetX = div.getTargetX();
		this.targetY = div.getTargetY();
		this.friends = div.getFriends();
		this.love = div.getLove();
		this.like = div.getEnjoy();
		this.dislike = div.getDislike();
		this.hate = div.getHate();
		this.targetId = div.getTargetId();
	}
}
