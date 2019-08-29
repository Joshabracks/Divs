package com.josh.divs.routines;

import java.util.List;

import com.josh.divs.models.Div;

public class FleeingPredicate implements Predicate {
	
	public FleeingPredicate() {}
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