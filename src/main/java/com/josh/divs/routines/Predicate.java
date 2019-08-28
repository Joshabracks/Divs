package com.josh.divs.routines;

import java.util.List;

import com.josh.divs.models.Div;

public interface Predicate {
	public void call(Div self, List<Div> ctx);
}