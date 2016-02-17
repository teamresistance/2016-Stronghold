package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class MoveLifterUp extends State {
	private String returnState;
	
	public MoveLifterUp(String returnState) {
		this.returnState = returnState;
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.lifterMotor.set(Constants.LIFTER_UP_SPEED);

	}

	@Override
	public void update() {
		if(IO.lifterUpperLimit.get()) {
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
