package com.josh.divs.tools;

import com.josh.divs.models.Div;

public class ActionTools {
	
	ActionTools(){}
	
	public Integer xProx(Div divA, Div divB) {
		return Math.abs(divA.getX() - divB.getX());
	}
	
	public Integer yProx(Div divA, Div divB) {
		return Math.abs(divA.getY() - divB.getY());
	}
	
}
