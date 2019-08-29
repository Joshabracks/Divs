package com.josh.divs.routines;

import java.util.List;

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
			this.predicate = (Predicate) new ChasingPredicate();
		} else if(attempted_symbol.compareTo("FLEEING") == 0) {
			this.predicate = new FleeingPredicate();
		} else if(attempted_symbol.compareTo("WIGGLING") == 0) {
			this.predicate = new WigglingPredicate();
		}
		return predicate != null;
	}
}