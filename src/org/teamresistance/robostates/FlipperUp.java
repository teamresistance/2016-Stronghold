package org.teamresistance.robostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class FlipperUp extends State {

	protected FlipperUp(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		IO.flipperSolenoid.set(true);
	}

	@Override
	public void update() {
		if(!JoystickIO.btnFlipper.isDown()) {
			gotoState("flipperUp");
		}
		
		if(JoystickIO.btnFlipper.onButtonPressed()) {
			gotoState("flipperDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {

	}

}
