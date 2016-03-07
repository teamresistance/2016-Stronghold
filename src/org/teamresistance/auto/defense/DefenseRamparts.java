package org.teamresistance.auto.defense;

import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseRamparts extends Defense {

	public static final double CROSS_SPEED = 0.5;

	@Override
	public boolean isReversed() {
		return false;
	}
	
	@Override
	public void whileCrossing() {
		throw new UnsupportedOperationException(); // TODO
	}
}
