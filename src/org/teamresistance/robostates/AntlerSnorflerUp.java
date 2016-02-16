package org.teamresistance.robostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class AntlerSnorflerUp extends State {

	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.snorflerMotor.set(0.0);
		IO.snorflerSolenoid.set(false);
		IO.antlerSolenoid.set(false);
	}

	@Override
	public void update() {
		if(JoystickIO.btnSnorfler.onButtonPressed()) {
			gotoState("SnorlferDown");
		}
		
		if(JoystickIO.btnAntler.onButtonPressed()) {
			gotoState("AntlersDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
