package org.teamresistance.robostates.lifter;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class LowerDrawbridge extends State {

	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.lifterTiltSolenoid.set(true);
		if(!IO.topLifterSwitch.get()) {
			((MoveLifterDown)stateMachine.getState("MoveLifterDown")).setReturnState(getName());
			gotoState("MoveLifterDown");
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

	
	
}
