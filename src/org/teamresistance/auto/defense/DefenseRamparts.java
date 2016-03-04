package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseRamparts extends Defense {

	public static final double CROSS_SPEED = 0.5;
	private double time = 0.0;

	@Override
	public boolean isReversed() {
		return false;
	}
	
	@Override
	public void whileCrossing() {	
		time += Time.getDelta();
		
		if (!IO.imu.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD) && time < 2.0) {
			//don't know if I can do it like this - check to make sure it doesn't freeze up
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		} else {
			this.setCrossed();
		}
	}
}