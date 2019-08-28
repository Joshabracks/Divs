package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import com.josh.divs.models.Div;

public class Routine {
	private String symbol;
	private Predicate predicate;
	public Routine() {
		symbol = "";
		predicate = null;
	}
	public Routine(String attempted_symbol) {
		this.setSymbol(attempted_symbol);
	}
	public boolean setSymbol(String attempted_symbol) {
		if(!tryResolve(attempted_symbol)) {
			this.symbol = "";
			return false;
		}
		this.symbol = attempted_symbol;
		return true;
	}
	public String getSymbol() {
		return symbol;
	}
	public boolean resolved() {
		return predicate != null;
	}
	public boolean run(Div self, List<Div> ctx) {
		if(self == null) {
			return false;
		}
		if(ctx.isEmpty()) {
			return false;
		}
		if(predicate == null) {
			return false;
		}
		predicate.call(self, ctx);
		return true;
	}
	private boolean tryResolve(String attempted_symbol) {
		if(attempted_symbol.compareTo("CHASING") == 0) {
			predicate = new ChasingPredicate();
		} else if(attempted_symbol.compareTo("FLEEING") == 0) {
			predicate = new FleeingPredicate();
		} else if(attempted_symbol.compareTo("WIGGLING") == 0) {
			predicate = new WigglingPredicate();
		}
		return predicate != null;
	}
}

/* BEHAVIORS */

class ChasingPredicate implements Predicate {
	@Override
	public void call(Div self, List<Div> ctx) {
		List<Div> enemies = self.getDivEnemies();
		if(enemies.isEmpty()) {
			return;
		}
		boolean exists = false;
		Div enemy = enemies.get(0);
		for(Div div : ctx) {
			if(self.getId() != div.getId() && div.getId() == enemy.getId()) {
				exists = true;
				if(Math.abs(self.getX() - div.getX()) <= 5 && Math.abs(self.getY() - div.getY()) <= 5) {
					self.setDamage(2);
					self.setTargetX(div.getX());
					self.setTargetY(div.getY());
					return;
				}
			}
		}
		if(!exists) {
			self.setDamage(0);
			enemies.remove(enemy);
		}
	}
}

class FleeingPredicate implements Predicate {
	@Override
	public void call(Div self, List<Div> ctx) {
		List<Div> enemies = self.getDivEnemies();
		if(enemies.isEmpty()) {
			return;
		}
		boolean exists = false;
		Div enemy = enemies.get(0);
		for(Div div : ctx) {
			if(self.getId() != div.getId() && div.getId() == enemy.getId()) {
				exists = true;
				if(Math.abs(self.getX() - div.getX()) <= 5 && Math.abs(self.getY() - div.getY()) <= 5) {
					// Get angle in radians
					double sx = (double)self.getX();
					double sy = (double)self.getY();
					double ex = (double)div.getX();
					double ey = (double)div.getY();
					double angle = Math.atan2(ey - sy, ex - sx);
					angle += Math.PI; // Rotate by PI
					sx -= Math.cos(angle) * 2.0; // Offset by unit vector-x doubled
					sy -= Math.sin(angle) * 2.0; // Offset by unit vector-y doubled
					self.setTargetX((int)sx);
					self.setTargetY((int)sy);
					return; // Target is placed away from div
				}
			}
		}
		if(!exists) {
			self.setDamage(0);
			enemies.remove(enemy);
		}
	}
}

class WigglingPredicate implements Predicate {
	private final Random rng;
	public WigglingPredicate() {
		rng = new Random();
	}
	@Override
	public void call(Div self, List<Div> ctx) {
		int deltaX = rng.nextInt(3);
		int deltaY = rng.nextInt(3);
		int x = self.getX();
		int y = self.getY();
		self.setX(x + deltaX);
		self.setY(y + deltaY);
	}
}