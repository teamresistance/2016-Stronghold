package org.teamresistance.auto.defense;

import org.teamresistance.auto.Defense;
import org.teamresistance.util.state.StateMachine;

public class DefenseDrawbridge extends Defense {

	private StateMachine lifterMachine;
	
	public DefenseDrawbridge(StateMachine lifterMachine) {
		this.lifterMachine = lifterMachine;
	}
	
	@Override
	public void beginCrossing() {
		lifterMachine.setState("LiftPortcullis");
	}

	@Override
	public void whileCrossing() {
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossing(false);
		}
	}
	
}
