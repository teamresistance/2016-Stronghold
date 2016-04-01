package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.annotation.Experimental;

@Experimental
public class DefenseRoughTerrain extends Defense {

	public static final double CROSS_SPEED = -0.645;
	public static final double CROSS_TIME = 2.5;

	private double startTime = 0.0;

	@Override
	public boolean isReversed() {
		return false;
	}

	@Override
	public void beforeCrossing() {
		startTime = Time.getTime();
	}
	
	@Override
	public void whileCrossing() {	
		if (Time.getTime() - startTime < CROSS_TIME) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0);
		} else {
//			if (IO.imu.isLevel( AutoConstants.ANGLE_ERROR_THRESHOLD,0, 0)) {
//				SmartDashboard.putBoolean("$$$$$$$$$$$$$$$$$$$$$$$CROSSD", true);
				this.setCrossed();
			//}
//			else {
//				IO.imu.turnTo(0, AutoConstants.ANGLE_ERROR_THRESHOLD);
//			}
		}
		
	}


}