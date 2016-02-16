package org.teamresistance.defenses;

import org.teamresistance.IO;
import org.teamresistance.util.Time;

public class ChevalDeFrise {
	Time timer;
	static double elapsed;
	Boolean isDone;
	
	/*
	 * Put antlers down
	 * drive forward
	 * raise antlers
	 * keep driving until level
	 */
	
	public void cross() {
		isDone = false;
		
		timer = new Time();
		
		IO.antlerSolenoid.set(true);
		
		while(elapsed<=0.5) {
			IO.robotDrive.arcadeDrive(0.5, 0);
		
			timer.update();
			elapsed = timer.getDelta();
		}
		
		IO.antlerSolenoid.set(false);
		
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
	}
}
