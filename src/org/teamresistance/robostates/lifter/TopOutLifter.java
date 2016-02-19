package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TopOutLifter extends ReturnState {

	private double startTime;
	
	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		IO.lifterMotor.set(Constants.LIFTER_TOPPING_SPEED);
		SmartDashboard.putBoolean("Topping", true);
	}

	@Override
	public void update() {
		if(Time.getTime() - startTime >=  Constants.LIFTER_TOPPING_TIME) {
			IO.lifterMotor.set(0);
			SmartDashboard.putBoolean("Topping", false);
			gotoReturnState();
		}
	}

	@Override
	public void onExit(StateTransition e) {

	}
	
}
