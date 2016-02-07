package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class ScaledDrive extends DriveTrain {

	protected ScaledDrive(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		//Empty
	}

	@Override
	public void onEntry(StateTransition e) {
		//Empty
	}

	@Override
	public void update() {
		super.update();
		IO.robotDrive.tankDrive(Util.scaleJoytick(getLeftY()), Util.scaleJoytick(getRightY()));
		if(JoystickIO.btnDriveMode.isDown()) {
			gotoState("DirectDrive");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		//Empty
	}

}
