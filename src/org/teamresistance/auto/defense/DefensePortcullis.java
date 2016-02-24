package org.teamresistance.auto.defense;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.state.StateMachine;

public class DefensePortcullis extends Defense {
	private double elapsed = 0.0;
	private boolean lifted = false;
	
	private StateMachine lifterMachine;
	
	public DefensePortcullis(StateMachine lifterMachine) {
		this.lifterMachine = lifterMachine;
	}

	@Override
	public void beginCrossing() { //set the flipper to down, and lower the lifter all the way down
		lifterMachine.setState("LiftPortcullis");
//		lowerFlipper();
//		lifterToBottom();
	}

	@Override
	public void whileCrossing() { //need to drive forward, raise flipper, lift motor to top, drive forward until level
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossing(false);
		}
//		elapsed += Time.getDelta();
//		if (elapsed<0.1) { //drive forward to the portcullis - will ideally implement collision detection here
//			IO.robotDrive.arcadeDrive(0.5, 0);
//		} 
//		else {
//			if(!lifted) {
//				while(!IO.topFlipperSwitch.get()) {
//					raiseFlipper();
//				}
//				lifterToTop();
//				lifted = true;
//			}
//		}
//		
//		if(!IO.imu.isLevel(10, 0, 0) && elapsed <= 2.0) {
//			// drive forward
//			IO.robotDrive.arcadeDrive(0.5, 0);
//		}
	}
	
	private void lifterToTop() {
		while(!IO.topLifterSwitch.get()) {
			IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);
		}
		IO.lifterMotor.set(0.0);
	}

	private void lifterToBottom() {
		while (!IO.bottomLifterSwitch.get()) { //drive the lifter down all the way
			IO.lifterMotor.set(Constants.LIFTER_UP_SPEED);
		}
		IO.lifterMotor.set(0.0);
	}
	
	private void lowerFlipper() {
		while (!IO.bottomFlipperSwitch.get()) {
			IO.flipperSolenoid.set(false);
		}
	}
	private void raiseFlipper() {
		while(!IO.topFlipperSwitch.get()) {
			IO.flipperSolenoid.set(true); // raise foot
		}
	}
}