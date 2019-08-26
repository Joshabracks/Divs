package com.josh.divs.tools;

import java.util.Random;

import org.springframework.util.StringUtils;

public class NameGenerator {
	
	public NameGenerator(){
		
	}
	
	private boolean isVowel(char a) {
		String vowe = "aeiou";
		for (int i = 0; i < vowe.length(); i++) {
			if (a == vowe.charAt(i)) {
				return true;
			}
		}
		return false;
	}
	
	public String name() {
		String name = "";
		String cons = "bcdfghjklmnpqrstvwxyz";
		String vowe = "aeiou";
		Random r = new Random();
		int now = r.nextInt(2);
		int prev = now;
		int bef = prev;
		int len = r.nextInt(5)+3;
		for (int i = 0; i < len; i++) {
			bef = prev;
			prev = now;
			now = r.nextInt(2);
			if ((bef == prev) && (now == prev)) {
				if (now == 1) {
					now = 2;
				}
				else {
					now = 1;
				}
			}
			if (now == 1) {
				name = name + cons.charAt(r.nextInt(cons.length()));
			}
			else {
				name = name + vowe.charAt(r.nextInt(vowe.length()));
			}
		}
		name = StringUtils.capitalize(name);
		return name;
	}
	
	public String name(String parent) {
		String name = "";
		String cons = "bcdfghjklmnpqrstvwxyz";
		String vowe = "aeiou";
		Random r = new Random();
		int now = r.nextInt(2);
		int prev = now;
		int bef = prev;
		int len = r.nextInt(5)+3;
		for (int i = 0; i < len; i++) {
			bef = prev;
			prev = now;
			now = r.nextInt(2);
			if ((bef == prev) && (now == prev)) {
				if (now == 1) {
					now = 2;
				}
				else {
					now = 1;
				}
			}
			if (now == 1) {
				name = name + cons.charAt(r.nextInt(cons.length()));
			}
			else {
				name = name + vowe.charAt(r.nextInt(vowe.length()));
			}
		}
		
		int start = r.nextInt(name.length());
		int pRange1 = r.nextInt(parent.length());
		if ((pRange1 + 3 > parent.length())) {
			pRange1 = pRange1 - 3;
		}
		if ((pRange1 - 3) < 0) {
			pRange1 = 0;
		}
		int pRange2 = pRange1 + 3;
		if (pRange2 > parent.length()) {
			pRange2 = parent.length();
		}
		parent = parent.substring(pRange1, pRange2);
		String nameTemp = name.substring(0, start) + parent;
		if (nameTemp.length() < 6) {
			nameTemp = nameTemp + name.substring(start, 3);
		}
		nameTemp = nameTemp.toLowerCase();
		name = nameTemp;
		
		bef = 0;
		prev = 1;
		now = 2;
		for (int i = 0; i < name.length(); i++) {
			bef = prev;
			prev = now;
			now = i;
			//ADD IF/!NULL STATEMENTS TO AVOID CRASH!!!!!
			if(name.length() > 2) {
				boolean one = isVowel(name.charAt(bef));
				boolean two = isVowel(name.charAt(prev));
				boolean three = isVowel(name.charAt(now));
				
				if ((one == two) && (two == three)) {
					System.out.println("from: " + name);
					name = name.substring(0, prev) + name.substring(prev + 1);
					i=0;
					bef = 0;
					prev = 1;
					now = 2;
				}
			}
			nameTemp = name;
		}
		
		name = StringUtils.capitalize(nameTemp);
		return name;

	}
	
	public String name(String p1, String p2) {
		String name = "";
		Random r = new Random();
		int start = r.nextInt(p1.length());
		int stop = r.nextInt(p1.length());
		if (start < stop) {
			p1 = p1.substring(start, stop);
		}
		else if (start > stop) {
			p1 = p1.substring(stop, start);
		}
		else {
			p1 = p1.substring(start);
		}
		start = r.nextInt(p2.length());
		stop = r.nextInt(p2.length());
		if (start < stop) {
			p2 = p2.substring(start, stop);
		}
		else if (start > stop) {
			p2 = p2.substring(stop, start);
		}
		else {
			p2 = p2.substring(start);
		}
		
		String parented = p1 + p2;
		name = name(parented);
		return name;
	}
	
	
}