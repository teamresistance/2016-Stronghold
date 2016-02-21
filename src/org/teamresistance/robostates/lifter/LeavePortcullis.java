package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.Robot;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class LeavePortcullis extends ReturnState {

	private double startTime;

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		IO.robotDrive.arcadeDrive(Constants.PORTCULLIS_LEAVE_SPEED, 0);
	}

	@Override
	public void update() {
		IO.robotDrive.arcadeDrive(Constants.PORTCULLIS_LEAVE_SPEED, 0);
		if(Time.getTime() - startTime >= Constants.PORTCULLIS_LEAVE_TIME) {
			if(Robot.robotState.equals("teleop")) {
				gotoState("TeleopLifterIdle");
			}
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.robotDrive.arcadeDrive(0,0);
	}

}
