package org.teamresistance.auto.defense;

import org.teamresistance.auto.Defense;
import org.teamresistance.util.state.StateMachine;

public class DefensePortcullis extends Defense {
	
	private StateMachine lifterMachine;
	
	public DefensePortcullis(StateMachine lifterMachine) {
		this.lifterMachine = lifterMachine;
	}

	@Override
	public void beginCrossing() { //set the flipper to down, and lower the lifter all the way down
		lifterMachine.setState("LiftPortcullis");
	}

	@Override
	public void whileCrossing() { //need to drive forward, raise flipper, lift motor to top, drive forward until level
		if (lifterMachine.getCurrentState().equals("TeleopLifterIdle")) {
			setCrossing(false);
		}
	}
}