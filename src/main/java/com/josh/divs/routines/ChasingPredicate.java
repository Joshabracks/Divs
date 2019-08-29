package com.josh.divs.routines;

import java.util.List;

import com.josh.divs.models.Div;

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