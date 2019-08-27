package com.josh.divs.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FirstMood {

	
	List<String> moods;
			
	public FirstMood(){
		List<String> list = Arrays.asList(new String[]{"^_^", ">_<", "o.O", ">_>", "<_<", "T_T", "O_O", "Q-Q", "@.@", "@_@"});
		this.moods = list;
	}
	
	public String get() {
		Random r = new Random();
		String mood = moods.get(r.nextInt(moods.size()));
		return mood;
	}
	
}
