package org.teamresistance.robostates.lifter;

import org.teamresistance.IO;
import org.teamresistance.Robot;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class RaiseFlipper extends State {

	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.flipperSolenoid.set(true);
	}

	@Override
	public void update() {
		if(IO.bottomFlipperSwitch.get()) {
			if(Robot.robotState.equals("teleop")) {
				((MoveLifterDown)stateMachine.getState("MoveLifterUp")).setReturnState("TeleopLifterIdle");
			} else {
				//Set auto return state
				//((MoveLifterDown)stateMachine.getState("MoveLifterUp")).setReturnState("TeleopLifterIdle");
			}
			gotoState("MoveLifterDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
