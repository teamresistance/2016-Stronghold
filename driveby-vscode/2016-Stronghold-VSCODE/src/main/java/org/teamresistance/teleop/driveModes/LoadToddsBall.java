package org.teamresistance.teleop.driveModes;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LoadToddsBall extends State {

	private double startTime;

	@Override
	public void onEntry(StateTransition e) {
		if(!IO.ballSensor.get()) {
			gotoState("Target");
		} else {
			startTime = Time.getTime();
	    	IO.snorflerMotor.set(Constants.BOULDER_LOAD_SPEED);
		}
	}

	@Override
	public void update() {
		SmartDashboard.putNumber("%%%%%%AngleMatch Being", 10000);
		if(Time.getTime() - startTime >= Constants.LOAD_TODDS_BALL_TIME) {
			//SmartDashboard.putNumber("AngleMatch Being", 10000);
//			gotoState("AngleMatch");
			gotoState("Target");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.snorflerMotor.set(0.0);
	}

}
