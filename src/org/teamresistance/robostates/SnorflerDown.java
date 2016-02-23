package org.teamresistance.robostates;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SnorflerDown extends State {

	private static boolean paused = false;

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
		if (JoystickIO.btnCancel.onButtonPressed() || IO.ballSensor.get()) {
			IO.snorflerMotor.set(0);
			gotoState("AntlerSnorflerUp");
		}

		if (JoystickIO.btnSnorfler.onButtonPressed()) {
			paused = !paused;
			if(!paused) {
				IO.snorflerMotor.set(Constants.SNORFLE_SPEED);
			} else {
				IO.snorflerMotor.set(0);
			}
		}
		if(JoystickIO.btnSnorflerReverse.onButtonPressed()) {
			IO.snorflerMotor.set(-1.0);
		} else if(JoystickIO.btnSnorflerReverse.onButtonReleased()) {
			if(!paused) {
				IO.snorflerMotor.set(Constants.SNORFLE_SPEED);
			} else {
				IO.snorflerMotor.set(0);
			}
		}
		
		if (JoystickIO.btnAntler.onButtonPressed()) {
			gotoState("AntlersDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.snorflerMotor.set(0);
		paused = false;
	}

}
