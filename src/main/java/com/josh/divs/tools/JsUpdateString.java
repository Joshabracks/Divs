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
		for (int i = allDivs.size()-1; i > 0; i--) {
			Div current = allDivs.get(i);
			dataString = dataString + current.getName() + ", ";
		}
		
		return dataString;
	}
}
