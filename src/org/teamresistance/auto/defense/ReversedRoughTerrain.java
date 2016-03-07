package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

/**
 * A dummy {@link Defense} implementation that drives straight with a constant crossing speed and
 * time; essentially a reversed {@link DefenseRoughTerrain}. Used only in testing.
 */
@Experimental
public class ReversedRoughTerrain extends Defense {

	public static final double CROSS_SPEED = -1 * DefenseRoughTerrain.CROSS_SPEED;
	public static final double CROSS_TIME = DefenseRoughTerrain.CROSS_TIME;

	private double startTime;

	@Override
	public boolean isReversed() {
		return true;
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