package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.io.NavXGyro;

public class AutonomousDefenses {
	NavXGyro imu = new NavXGyro();
	
	public void crossMoat() {
		//check starting alignment
		int yawAngle = (int) imu.getYawAngle();
		int rollAngle = (int) imu.getRollAngle();
		int pitchAngle = (int) imu.getPitchAngle();
		
		
		//use gyro to continue checking alignment
		while(!imu.isLevel(10, rollAngle, pitchAngle)) {
			//drive forward
			IO.robotDrive.arcadeDrive(0.5, 0);
		} 
		
		imu.turnTo(10, yawAngle); //straighten back out once off
		//keep track of position to determine when off defense
	}
	public void crossRamparts() {
		//check starting alignment
				int yawAngle = (int) imu.getYawAngle();
				int rollAngle = (int) imu.getRollAngle();
				int pitchAngle = (int) imu.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!imu.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				imu.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
	}
	public void crossRockWall() {
		//check starting alignment
				int yawAngle = (int) imu.getYawAngle();
				int rollAngle = (int) imu.getRollAngle();
				int pitchAngle = (int) imu.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!imu.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				imu.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
	}
	public void crossRoughTerrain() {
		//check starting alignment
				int yawAngle = (int) imu.getYawAngle();
				int rollAngle = (int) imu.getRollAngle();
				int pitchAngle = (int) imu.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!imu.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				imu.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
	}
}

/*public void crossDrawbridge() {
		
	}
	public void crossSallyPort() {
		
	}
	public void crossPortcullis() {
		
	}
	public void crossChevalDeFrise() {
		
	}*/
