package org.teamresistance.defenses;

import org.teamresistance.IO;
import org.teamresistance.defenses.DefenseMaster;

	public class RockWall {
		Boolean isDone;
		public void cross() {
			isDone = false;
			
			//check starting alignment
			int yawAngle = (int) DefenseMaster.gyro.getYawAngle();
			int rollAngle = (int) DefenseMaster.gyro.getRollAngle();
			int pitchAngle = (int) DefenseMaster.gyro.getPitchAngle();
			
			
			//use gyro to continue checking alignment
			while(!DefenseMaster.gyro.isLevel(10, rollAngle, pitchAngle)) {
				//drive forward
				IO.robotDrive.arcadeDrive(0.5, 0);
			} 
			
			DefenseMaster.gyro.turnTo(10, yawAngle); //straighten back out once off
			//keep track of position to determine when off defense
			
			isDone = true;
		}
}
