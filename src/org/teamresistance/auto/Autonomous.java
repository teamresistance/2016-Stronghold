package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;

 /*
 * Four states: init, defense crossing, driving, targeting/shooting
 */

public class Autonomous extends State {
	
	Time clock = new Time();

	protected Autonomous(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		gotoState("startPos");
	}

	@Override
	public void update() {
		if(posCorrect()) {
			gotoState("CrossDefense");
		}
		
	}

	@Override
	public void onExit(StateTransition e) {

	}

}
