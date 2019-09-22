package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseCheval extends Defense {

	
	private static final double DELAY = 2.0;
	private static final double CROSS_SPEED = -0.8;
	
	private double startTime = 0.0;

	@Override
	public boolean isReversed() {
		return false;
	}

	@Override
	public void beforeCrossing() {
		// Raise the antlers
		IO.antlerSolenoid.set(true);
		startTime = Time.getTime();
	}

	@Override
	public void whileCrossing() {
		float a = 1.25f;
		IO.antlerSolenoid.set(true);
		if(Time.getTime() - startTime <= DELAY) {
		} else if (Time.getTime() - startTime <= DELAY + a) {
			// Don't know if I can do it like this - check to make sure it doesn't freeze up
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0);
		} else if(Time.getTime() - startTime <= DELAY + 1.75 + a) {
			IO.robotDrive.arcadeDrive(-0.65, 0);
		} else {
			// Try leaving the antlers down for the full crossing
			IO.antlerSolenoid.set(false);
			this.setCrossed();
		}
	}
}