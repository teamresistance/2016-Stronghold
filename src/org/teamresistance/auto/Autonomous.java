package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.auto.AutoMaster;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;

 /*
 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
 */

public class Autonomous extends State {
	StateMachine autoStates;
	Time clock;

	protected Autonomous(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		clock = new Time();
		autoStates = new StateMachine();
		autoStates.addState(CrossDefense.class,"CrossDefense");
		autoStates.addState(DriveToTower.class, "DriveToTower");
		autoStates.addState(AutoTargeting.class, "Targeting");
		
		
	}

	@Override
	public void onEntry(StateTransition e) {
		//autoStates.setState("startPos"); //if there's any reorientation happening, which is unlikely
		autoStates.setState("CrossDefense");
	}

	@Override
	public void update() {
		//if(AutoMaster.position.posCorrect()) {
		//	gotoState("CrossDefense");
		//}
		autoStates.update();
		
	}

	@Override
	public void onExit(StateTransition e) {

	}

}
