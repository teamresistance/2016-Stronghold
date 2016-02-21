package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class MoveLifterDown extends ReturnState {

	@Override
	public void onEntry(StateTransition e) {
		IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);

	}

	@Override
	public void update() {
		if(IO.bottomLifterSwitch.get()) {
			stop();
		}
	}
	
	private void stop() {
		IO.lifterMotor.set(0.0);
		gotoReturnState();
	}

}
