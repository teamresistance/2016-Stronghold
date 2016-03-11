package org.teamresistance.teleop.driveModes;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.Robot;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class Shoot extends ReturnState {
	
	private double startTime;

	@Override
	public void onEntry(StateTransition e) {
		IO.shooterSolenoid.set(true);
		startTime = Time.getTime();
	}

	@Override
	public void update() {
		if(Time.getTime() - startTime >= Constants.SHOOTER_DELAY) {
			gotoReturnState();
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.shooterSolenoid.set(false);
		if(Robot.robotState.equals("teleop")) {
			Robot.teleop.exitIdleDrive();
		}
	}

}
