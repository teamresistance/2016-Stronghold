package org.teamresistance.robostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class AntlersDown extends State {

	protected AntlersDown(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		IO.snorflerMotor.set(0.0);
		IO.snorflerSolenoid.set(false);
		IO.antlerSolenoid.set(true);
	}

	@Override
	public void update() {
		if(!JoystickIO.btnAntler.isDown()) {
			gotoState("AntlerSnorflerUp");
		}
		
		if(JoystickIO.btnSnorfler.onButtonPressed()) {
			gotoState("SnorflerDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {

	}

}