package com.josh.divs.tools;

import java.util.List;

import com.josh.divs.models.Div;
import com.josh.divs.services.DivService;

public class JsUpdateString {
	DivService divs;
	
	public JsUpdateString(DivService divs) {
		this.divs = divs;
	}
	
	public String getData() {
		String dataString = "";
		List<Div> allDivs = divs.allDivs();
		for (int i = allDivs.size()-1; i >= 0; i--) {
			Div current = allDivs.get(i);
			dataString = dataString + "!id?" + current.getId() + "#";
			dataString = dataString + "!name?" + current.getName() + "#";
			dataString = dataString + "!color?" + current.getColor() + "#";
			dataString = dataString + "!outline?" + current.getOutline() + "#";
			dataString = dataString + "!mood?" + current.getMood() + "#";
			dataString = dataString + "!radius?" + current.getRadius() + "#";
			dataString = dataString + "!end#";
		}
		
		return dataString;
	}
	
	public String getData(Div div) {
		String dataString = "";
		dataString = dataString + "!id?" + div.getId() + "#";
		dataString = dataString + "!name?" + div.getName() + "#";
		dataString = dataString + "!color?" + div.getColor() + "#";
		dataString = dataString + "!outline?" + div.getOutline() + "#";
		dataString = dataString + "!mood?" + div.getMood() + "#";
		dataString = dataString + "!radius?" + div.getRadius() + "#";
		dataString = dataString + "!end#";
		return dataString;
	}
}
