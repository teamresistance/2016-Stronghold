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
	protected void beforeCrossing() {
		// Enter the LiftPortcullis state, which will handle all the heavy lifting and driving
		lifterMachine.setState("LiftPortcullis");
	}

	@Override
	public void whileCrossing() {
		// We're done crossing the defense once the lifter machine is done lifting
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossed();
		}
	}
}
