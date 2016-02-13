package org.teamresistance.robostates;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class SnorflerDown extends State {
	
	private static boolean paused = false;
	
	protected SnorflerDown(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		IO.snorflerMotor.set(Constants.SNORFLE_SPEED);
		IO.snorflerSolenoid.set(true);
		IO.antlerSolenoid.set(false);
	}

	@Override
	public void update() {
		if(JoystickIO.btnCancel.onButtonPressed() || IO.ballSensor.get()) {
			gotoState("AntlerSnorflerUp");
		}
		
		if(JoystickIO.btnSnorfler.onButtonPressed()) {
			if(paused) {
				paused = false;
				IO.snorflerMotor.set(Constants.SNORFLE_SPEED);
			} else {
				paused = true;
				IO.snorflerMotor.set(0.0);
			}
		}
		
		if(JoystickIO.btnAntler.onButtonPressed()) {
			gotoState("AntlersDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {

	}

}
