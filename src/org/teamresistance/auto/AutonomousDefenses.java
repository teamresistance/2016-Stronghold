package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.NavXGyro;

public class AutonomousDefenses {
	NavXGyro gyro = new NavXGyro();
	
	public void crossMoat() {
		//check starting alignment
		int yawAngle = (int) gyro.getYawAngle();
		int rollAngle = (int) gyro.getRollAngle();
		int pitchAngle = (int) gyro.getPitchAngle();
		
		
		//use gyro to continue checking alignment
		while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
			//drive forward
			IO.robotDrive.arcadeDrive(0.5, 0);
		} 
		
		gyro.turnTo(10, yawAngle); //straighten back out once off
		//keep track of position to determine when off defense
	}
	public void crossRamparts() {
		//check starting alignment
				int yawAngle = (int) gyro.getYawAngle();
				int rollAngle = (int) gyro.getRollAngle();
				int pitchAngle = (int) gyro.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				gyro.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
	}
	public void crossRockWall() {
		//check starting alignment
				int yawAngle = (int) gyro.getYawAngle();
				int rollAngle = (int) gyro.getRollAngle();
				int pitchAngle = (int) gyro.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				gyro.turnTo(10, yawAngle); //straighten back out once off
				//keep track of position to determine when off defense
	}
	public void crossRoughTerrain() {
		//check starting alignment
				int yawAngle = (int) gyro.getYawAngle();
				int rollAngle = (int) gyro.getRollAngle();
				int pitchAngle = (int) gyro.getPitchAngle();
				
				
				//use gyro to continue checking alignment
				while(!gyro.isLevel(10, rollAngle, pitchAngle)) {
					//drive forward
					IO.robotDrive.arcadeDrive(0.5, 0);
				} 
				
				gyro.turnTo(10, yawAngle); //straighten back out once off
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
