package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.util.SwingDetection;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseRockWall extends Defense {

	public static final double CROSS_SPEED = 0.5;
    public static final double CROSS_TIME = 2.0;

	private final SwingDetection swingDetection;

	private double startTime;
	private double swingNaught = 0.0;

	public DefenseRockWall(SwingDetection swingDetection) {
		this.swingDetection = swingDetection;
	}

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
		boolean isSwinging = swingDetection.isSwinging();
		if (!isSwinging && Time.getTime() - startTime < 1.0) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0);
		} else if (isSwinging && swingNaught < 0.1) {
			IO.robotDrive.arcadeDrive(0, 0);
			swingNaught += Time.getDelta();
		} else if (IO.imu.isLevel(10, 0, 0) && Time.getTime() - startTime < CROSS_TIME) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		} else {
			setCrossed();
		}
	}
}
