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
	protected void beforeCrossing() {
		// Enter the LowerDrawbridge state, which will handle all the heavy lifting and driving
		lifterMachine.setState("LowerDrawbridge");
	}

	@Override
	public void whileCrossing() {
		// We're done crossing the defense once the lifter machine is done lifting
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossed();
		}
	}
}
