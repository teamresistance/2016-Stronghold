package org.teamresistance.robostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;


public class Shooter extends StateMachine {
	
	protected Shooter(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}
	
	public void onEntry(StateTransition e) {
		IO.shooterSolenoid.set(false);
	}

	@Override
	public void update() {
		if(!JoystickIO.btnShooter.isDown()) {
			gotoState("DontShoot");
		}
		
		if(JoystickIO.btnShooter.onButtonPressed()) {
			gotoState("ShootReady");
		}
	}

	public void onExit(StateTransition e) {

	}
	
}
