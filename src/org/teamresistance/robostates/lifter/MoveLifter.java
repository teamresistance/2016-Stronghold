package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class MoveLifter extends State {

	private String returnState;
	
	public MoveLifter(String returnState) {
		this.returnState = returnState;
	}
	
	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		if(IO.lifterLowerLimit.get()) {
			IO.lifterMotor.set(Constants.LIFTER_UP_SPEED);
		} else if(IO.lifterUpperLimit.get()) {
			IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);
		} else {
			if(IO.lifterMotor.get() < 0.0) {
				IO.lifterMotor.set(Constants.LIFTER_UP_SPEED);
			} else {
				IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);
			}
		}
	}

	@Override
	public void update() {
		if(IO.lifterUpperLimit.get() && IO.lifterMotor.get() > 0.0) {
			stop();
		} else if(IO.lifterLowerLimit.get() && IO.lifterMotor.get() < 0.0) {
			stop();
		}
	}
	
	private void stop() {
		IO.lifterMotor.set(0.0);
		gotoState(returnState);
	}
	
	@Override
	public void onExit(StateTransition e) {

	}

	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}
}
