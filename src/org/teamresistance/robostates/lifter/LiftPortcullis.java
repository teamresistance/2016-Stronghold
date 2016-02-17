package org.teamresistance.robostates.lifter;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class LiftPortcullis extends State {
	
	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.lifterTiltSolenoid.set(false);
		if(!IO.lifterLowerLimit.get()) {
			((MoveLifterDown)stateMachine.getState("MoveLifterDown")).setReturnState(getName());
			gotoState("MoveLifterDown");
		} else {
			gotoState("RaiseFoot");
		}		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
