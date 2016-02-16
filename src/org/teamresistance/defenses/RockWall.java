package org.teamresistance.defenses;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.defenses.DefenseMaster;

	public class RockWall {
		Time timer;
		static double elapsed;
		Boolean isDone;
		
		public void cross() {
			
			isDone = false;
			
			timer = new Time();
			
			while(elapsed<=0.5) {
				IO.robotDrive.arcadeDrive(0.5, 0);
			
				timer.update();
				elapsed = timer.getDelta();
			}
			
			//check starting alignment
			/*int yawAngle = (int) DefenseMaster.gyro.getYawAngle();
			int rollAngle = (int) DefenseMaster.gyro.getRollAngle();
			int pitchAngle = (int) DefenseMaster.gyro.getPitchAngle();
			*/
			
			//use gyro to continue checking alignment
			while(!DefenseMaster.imu.isLevel(10, 0, 0) && elapsed<=1) {
				//drive forward
				IO.robotDrive.arcadeDrive(0.5, 0);
				
				timer.update();
				elapsed = timer.getDelta();
				
			} 
			
			//DefenseMaster.gyro.turnTo(10, 0); //straighten back out once off
			//keep track of position to determine when off defense
			
			isDone = true;
		}
}
