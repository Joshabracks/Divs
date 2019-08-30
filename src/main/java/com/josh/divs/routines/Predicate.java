package com.josh.divs.routines;

import java.util.List;

import com.josh.divs.tools.Divvy;

public interface Predicate {
	public Divvy call(Divvy self, List<Divvy> ctx);
}