package org.teamresistance.robostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class ShootReady extends State {

	protected ShootReady(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		IO.shooterSolenoid.set(false);
	}

	@Override
	public void update() {
		if(!JoystickIO.btnAntler.isDown()) {
			gotoState("DontShoot");
		}
		

	}

	@Override
	public void onExit(StateTransition e) {

	}

}
