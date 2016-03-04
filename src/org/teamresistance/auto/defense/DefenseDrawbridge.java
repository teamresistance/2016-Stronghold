package org.teamresistance.auto.defense;

import org.teamresistance.util.annotation.Experimental;
import org.teamresistance.util.state.StateMachine;

@Experimental
public class DefenseDrawbridge extends Defense {

	private final StateMachine lifterMachine;
	
	public DefenseDrawbridge(StateMachine lifterMachine) {
		this.lifterMachine = lifterMachine;
	}

	@Override
	public boolean isReversed() {
		return true;
	}

	@Override
	public void beforeCrossing() {
		lifterMachine.setState("LiftPortcullis");
	}

	@Override
	public void whileCrossing() {
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossed();
		}
	}
	
}
