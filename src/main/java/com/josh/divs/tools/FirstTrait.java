package com.josh.divs.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FirstTrait {
	private List<String> traits;
	public FirstTrait() {
		List<String> list = Arrays.asList(new String[]{"friendly", "fighter", "antisocial", "sociopath", "hermit"});
		this.traits = list;
	}
	public String get() {
		Random r = new Random();
		String trait = traits.get(r.nextInt(traits.size()));
		return trait;
	}
}
