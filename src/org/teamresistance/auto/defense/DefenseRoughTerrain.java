package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;

	 /*
	 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class DefenseRoughTerrain extends Defense {
	
	private static final double CROSS_SPEED = .5;
	
	private double time = 0.0;
	
	@Override
	public void beginCrossing() {
		
	}
	
	@Override
	public void whileCrossing() {	
		time += Time.getDelta();
		
		if(time < 2.0) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		} else {
			if (IO.imu.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD)) {
				this.setCrossing(false);
			} else {
				IO.imu.turnTo(0, AutoConstants.ANGLE_ERROR_THRESHOLD);
			}
		}
		
	}


}