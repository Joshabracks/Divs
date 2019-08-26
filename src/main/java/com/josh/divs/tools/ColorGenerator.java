package com.josh.divs.tools;

import java.util.Random;

public class ColorGenerator {
	
	public ColorGenerator() {
		
	}
	
	public String mix() {
		Random rando = new Random();
		int r = rando.nextInt(255);
		int g = rando.nextInt(255);
		int b = rando.nextInt(255);
		
		String color = "rgb(" + r + "," + g +"," + b + ")";
		return color;
	}
}
