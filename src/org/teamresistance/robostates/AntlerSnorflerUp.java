package org.teamresistance.robostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		SmartDashboard.putBoolean("Updating", true);
		if(JoystickIO.btnSnorfler.isDown() && !IO.ballSensor.get()) {
			SmartDashboard.putBoolean("Snorfler", true);
			gotoState("SnorflerDown");
		}
		
		if(JoystickIO.btnAntler.onButtonPressed()) {
			SmartDashboard.putBoolean("Antler", true);
			gotoState("AntlersDown");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
