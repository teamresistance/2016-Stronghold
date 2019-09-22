package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class MoveLifterUp extends ReturnState {

	@Override
	public void onEntry(StateTransition e) {
		IO.lifterMotor.set(Constants.LIFTER_UP_SPEED);

	}

	@Override
	public void update() {
		if(IO.topLifterSwitch.get()) {
			stop();
		} else if(JoystickIO.btnCancel.isDown()) {
			gotoState("TeleopLifterIdle");
		}
	}
	
	private void stop() {
		IO.lifterMotor.set(0.0);
		gotoReturnState();
	}

}
