package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;



import com.josh.divs.tools.Divvy;

@Service
public class WigglingPredicate implements Predicate {
	private final Random rng;
	public WigglingPredicate() {
		rng = new Random();
	}
	
	
	@Override
	public Divvy call(Divvy self, List<Divvy> ctx) {
		int deltaX = rng.nextInt(200)-100;
		int deltaY = rng.nextInt(200)-100;
		int x = self.x;
		int y = self.x;
		x = x + deltaX;
		y = y + deltaY;
		if (x < 0) {
			x = 20;
		}
		if (y < 0) {
			y = 20;
		}
		if (x > 3000) {
			x = 2990;
		}
		if (y > 3000) {
			y = 2990;
		}
		
		self.status = "idle";
		self.targetX = x;
		self.targetY = y;
		return self;
	}
}