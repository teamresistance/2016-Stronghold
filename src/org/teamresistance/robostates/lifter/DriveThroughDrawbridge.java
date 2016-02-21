package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveThroughDrawbridge extends State {

	private double startTime;

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_DRIVE_TRHOUGH_SPEED, 0);
	}

	@Override
	public void update() {
		if(Time.getTime() - startTime >= Constants.DRAWBRIDGE_FLIPPER_DELAY) {
			IO.flipperSolenoid.set(false);
		}
		if(Time.getTime() - startTime >= Constants.DRAWBRIDGE_DRIVE_THROUGH_TIME) {
			IO.robotDrive.arcadeDrive(0.0, 0.0);
			gotoState("TeleopLifterIdle");
		} else {
			IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_DRIVE_TRHOUGH_SPEED, 0);
		}
	}

}
