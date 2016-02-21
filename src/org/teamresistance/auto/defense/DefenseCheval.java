package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Autonomous;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateTransition;

	 /*
	 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class DefenseCheval extends Defense {
	
	private double time = 0.0;

	@Override
	public void beginCrossing() {
		
	}

	@Override
	public void whileCrossing() {	
		time += Time.getDelta();
		
		if(Time.getDelta()>0.5) {
			Autonomous.AntlerMachine.getState("AntlersUp");
		}
		
		if(!DefenseMaster.imu.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD) && time<2.0) {
			//don't know if I can do it like this - check to make sure it doesn't freeze up
			
			IO.robotDrive.arcadeDrive(AutoConstants.CHEVAL_CROSS_SPEED, 0.0);
		}
		else {
			
			//turn
			//drive
			//turn
			AutoMaster.driver.drive(false);
			
		}
		
	}


}