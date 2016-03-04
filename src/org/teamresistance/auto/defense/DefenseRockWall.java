package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.util.SwingDetection;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseRockWall extends Defense {
	
	public static final double CROSS_SPEED = .5;
	private final SwingDetection swingDetection;

	private double time = 0.0;
	private double swingNaught = 0.0;

	public DefenseRockWall(SwingDetection swingDetection) {
		this.swingDetection = swingDetection;
	}

	@Override
	public boolean isReversed() {
		return true;
	}
	
	@Override
	public void whileCrossing() {
		double delta = Time.getDelta();
		time += delta;

		boolean isSwinging = swingDetection.isSwinging();
		if (!isSwinging && time < 1.0) {
			//don't know if I can do it like this - check to make sure it doesn't freeze up	
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		} else if (isSwinging && swingNaught < 0.1) {
			IO.robotDrive.arcadeDrive(0.0, 0.0);
			swingNaught += delta;
		} else if (IO.imu.isLevel(10, 0, 0) && time < 2.0) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		} else {
			this.setCrossed();
		}
	}

}