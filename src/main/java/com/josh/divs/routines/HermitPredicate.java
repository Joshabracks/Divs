package com.josh.divs.routines;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.josh.divs.tools.ActionTools;
import com.josh.divs.tools.Divvy;

@Service
public class HermitPredicate {

	Random rando;
	ActionTools tools;
	
	public HermitPredicate() {
		Random ran = new Random();
		this.rando = ran;
		ActionTools too = new ActionTools();
		this.tools = too;
	}
	
	public Divvy call(Divvy self, List<Divvy> allDivvy) {
		Integer present = 0;
		for (int i = 0; i < allDivvy.size(); i++) {
			if ( (tools.xProx(self, allDivvy.get(i)) < 300) && (tools.yProx(self, allDivvy.get(i)) < 300) ) {
				present = present + 1;;
			}
			if (present > 5) {
				Integer direct = rando.nextInt(1);
				if (direct > 0) {
					self.targetX = 0;
					self.targetY = 2000;
				}
				else {
					self.targetY = 0;
					self.targetX = 2000;
				}
			}
			return self;
		}
		
		WigglingPredicate pred = new WigglingPredicate();
		self = pred.call(self,  allDivvy);
		return self;
	}
}
