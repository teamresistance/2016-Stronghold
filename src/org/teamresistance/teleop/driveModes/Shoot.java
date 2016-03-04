package org.teamresistance.teleop.driveModes;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.Robot;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class Shoot extends ReturnState {
	
	private double time=0;

	@Override
	public void onEntry(StateTransition e) {
		IO.shooterSolenoid.set(true);
	}

	@Override
	public void update() {
		time+=Time.getDelta();
		if(time >= Constants.SHOOTER_DELAY) {
			gotoReturnState();
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.shooterSolenoid.set(false);
		Robot.teleop.exitIdleDrive();
	}

}
