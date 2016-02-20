package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.auto.SwingDetection;

	 /*
	 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class DefenseRockWall extends Defense {
	
	private double time = 0.0;
	private double swingNaught = 0;
	SwingDetection swing;
	private double delta=0;
	@Override
	public void beginCrossing() {
		swing = new SwingDetection();
	}
	
	@Override
	public void whileCrossing() {
		delta = Time.getDelta();
		time += delta;
		
		if(!swing.detected() && time<1.0) {
			//don't know if I can do it like this - check to make sure it doesn't freeze up	
			IO.robotDrive.arcadeDrive(AutoConstants.ROCK_WALL_CROSS_SPEED, 0.0);
		}
		else {
			
			if(swing.detected()&&swingNaught<0.1) {
				IO.robotDrive.arcadeDrive(0.0, 0.0);
				swingNaught+=delta;
			}else {
				if(IO.imu.isLevel(10, 0, 0) && time < 2.0) {
					IO.robotDrive.arcadeDrive(AutoConstants.ROCK_WALL_CROSS_SPEED, 0.0);
				}else {
					this.setCrossing(false);
				}
			}
			
		}
		
	}


}