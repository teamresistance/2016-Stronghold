package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseRoughTerrain extends Defense {

	public static final double CROSS_SPEED = 0.645;
	public static final double CROSS_TIME = 2.5;

	private double startTime;

	@Override
	public boolean isReversed() {
		return false;
	}

	@Override
	protected void beforeCrossing() {
		startTime = Time.getTime();
	}

	@Override
	public void whileCrossing() {
		if (Time.getTime() - startTime < CROSS_TIME) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0);
		} else {
			setCrossed();
		}
	}
}
