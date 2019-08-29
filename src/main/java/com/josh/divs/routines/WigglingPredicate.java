package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.models.Div;
import com.josh.divs.repositories.DivRepository;

@Service
public class WigglingPredicate implements Predicate {
	DivRepository divs;
	private final Random rng;
	public WigglingPredicate(DivRepository divs) {
		rng = new Random();
		this.divs = divs;
		
		
	}
	
	
	@Override
	public void call(Div self, List<Div> ctx) {
		int deltaX = rng.nextInt(200)-100;
		int deltaY = rng.nextInt(200)-100;
		int x = self.getX();
		int y = self.getY();
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
		
		self.setStatus("idle");
		self.setTargetX(x);
		self.setTargetY(y);
		divs.save(self);
	}
}