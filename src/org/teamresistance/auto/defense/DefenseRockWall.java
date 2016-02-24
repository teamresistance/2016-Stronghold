package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Defense;
import org.teamresistance.auto.SwingDetection;
import org.teamresistance.util.Time;

	 /*
	 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class DefenseRockWall extends Defense {
	
	private static final double CROSS_SPEED = .5;
	
	private double time = 0.0;
	//private double swingNaught = 0;
	private double delta =0;
	
	//private SwingDetection swing;
	
	@Override
	public void beginCrossing() {
		swing = new SwingDetection();
	}
	
	@Override
	public void whileCrossing() {
		delta = Time.getDelta();
		time += delta;
		
		if(!IO.imu.isStraight(AutoConstants.ANGLE_ERROR_THRESHOLD, 0)) {
			IO.imu.turnTo(0, AutoConstants.ANGLE_ERROR_THRESHOLD);
		}
		
		if(/*!swing.detected() && */time<1.0) {
			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		}
		else {
			
			//if(swing.detected()&&swingNaught<0.1) {
			//	IO.robotDrive.arcadeDrive(0.0, 0.0);
			//	swingNaught+=delta;
				if(!IO.imu.isLevel(10, 0, 0) && time < 2.0) {
					IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
				}else {
					this.setCrossing(false);
			}
		}
		
	}
		
}