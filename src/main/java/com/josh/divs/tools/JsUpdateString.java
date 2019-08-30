package com.josh.divs.tools;

import java.util.List;

import com.josh.divs.models.Div;

public class JsUpdateString {

	
	public JsUpdateString() {
	}
	
	public String getData(List<Divvy> divs) {
		String dataString = "";
		for (int i = divs.size()-1; i >= 0; i--) {
			Divvy current = divs.get(i);
			dataString = dataString + "!id?" + current.id + "#";
			dataString = dataString + "!name?" + current.name + "#";
			dataString = dataString + "!color?" + current.color + "#";
			dataString = dataString + "!outline?" + current.outline + "#";
			dataString = dataString + "!mood?" + current.mood + "#";
			dataString = dataString + "!radius?" + current.radius + "#";
			dataString = dataString + "!x?" + current.x + "#";
			dataString = dataString + "!y?" + current.y + "#";
			dataString = dataString + "!like?" + current.like + "#";
			dataString = dataString + "!love?" + current.love + "#";
			dataString = dataString + "!hate?" + current.hate + "#";
			dataString = dataString + "!dislike?" + current.dislike + "#";
			dataString = dataString + "!action?" + current.action + "#";
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
		dataString = dataString + "!x?" + div.getX() + "#";
		dataString = dataString + "!y?" + div.getY() + "#";
		dataString = dataString + "!like?" + div.getEnjoy() + "#";
		dataString = dataString + "!love?" + div.getLove() + "#";
		dataString = dataString + "!hate?" + div.getHate() + "#";
		dataString = dataString + "!dislike?" + div.getDislike() + "#";
		dataString = dataString + "!action?Is chillin'#";
		dataString = dataString + "!end#";
		return dataString;
	}
}
