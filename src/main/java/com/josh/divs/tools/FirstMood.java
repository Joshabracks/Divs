package com.josh.divs.tools;

import java.util.Arrays;
import java.util.List;

public class FirstMood {

	
	List<String> moods;
			
	public FirstMood(){
		List<String> list = Arrays.asList(new String[]{"^_^", ">_<", "o.O", ">_>", "<_<", "T_T"});
		this.moods = list;
	}
	
	public String get() {
		System.out.println(moods);
		return "^_^";
	}
	
}
