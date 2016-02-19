package org.teamresistance.teleop.driveModes;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class LoadToddsBall extends State {

	private double startTime;
	
	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		IO.snorflerMotor.set(Constants.BOULDER_LOAD_SPEED);
	}

	@Override
	public void update() {
		if(Time.getTime() - startTime >= Constants.LOAD_TODDS_BALL_TIME) {
			gotoState("AngleMatch");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.snorflerMotor.set(0.0);
	}

}
