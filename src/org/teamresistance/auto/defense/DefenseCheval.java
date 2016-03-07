package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseCheval extends Defense {

	public static final double CROSS_SPEED = 0.5;

	@Override
	public boolean isReversed() {
		return false;
	}

	@Override
	protected void beforeCrossing() {
		// Raise the antlers
		IO.antlerSolenoid.set(true);
	}

	@Override
	public void whileCrossing() {
		// Note: try leaving the antlers down for the full crossing)
		throw new UnsupportedOperationException(); // TODO
	}
}
