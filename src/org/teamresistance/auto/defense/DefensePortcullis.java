package org.teamresistance.auto.defense;

import org.teamresistance.util.annotation.Experimental;
import org.teamresistance.util.state.StateMachine;

@Experimental
public class DefensePortcullis extends Defense {
	
	private final StateMachine lifterMachine;
	
	public DefensePortcullis(StateMachine lifterMachine) {
		this.lifterMachine = lifterMachine;
	}

	@Override
	public boolean isReversed() {
		return true;
	}

	@Override
	public void beforeCrossing() {
		// Set the flipper to down, and lower the lifter all the way down
		lifterMachine.setState("LiftPortcullis");
	}

	@Override
	public void whileCrossing() {
		// Need to drive forward, raise flipper, lift motor to top, drive forward until level
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossed();
		}
	}
}