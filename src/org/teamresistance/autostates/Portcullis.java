package org.teamresistance.defenses;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;

public class Portcullis {
	Time timer;
	static double elapsed;
	boolean isDone;
	
	/*drive forward
	 * raise foot till limit switch hit
	 * spin screw motor until limit switch hit
	 * drive forward until level
	 */
	
	public void cross() {
		isDone = false;
		
		timer = new Time();
		
		while(elapsed<=0.2) {
			IO.robotDrive.arcadeDrive(0.5, 0);
		
			timer.update();
			elapsed = timer.getDelta();
		}
		
		//check starting alignment
		/*int yawAngle = (int) DefenseMaster.gyro.getYawAngle();
		int rollAngle = (int) DefenseMaster.gyro.getRollAngle();
		int pitchAngle = (int) DefenseMaster.gyro.getPitchAngle();
		*/
		
		//raise foot
		while(IO.footSwitch.get()) {
		IO.footSolenoid.set(false);
		}
		
		//in case there's a need to roll back a bit
		//IO.robotDrive.arcadeDrive(0.1,0);
		
		//spin screw
		while(IO.screwSwitch.get()) {
			IO.gateLifterMotor.set(Constants.SCREW_THREAD_SPEED);
		}
		IO.gateLifterMotor.set(0);
		//immediately drives forward; will need some modification to speed and to timing
		//use gyro to continue checking alignment, or cuts to next stage once a certain time has passed
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
