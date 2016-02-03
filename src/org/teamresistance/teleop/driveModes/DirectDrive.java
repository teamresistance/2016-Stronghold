package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class DirectDrive extends DriveTrain {

	protected DirectDrive(StateMachine stateMachine, String name) {
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
		IO.robotDrive.tankDrive(getLeftY(), getRightY());
		if(IO.leftJoystick.getRawButton(3)) {
			gotoState("ScaledDrive");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		//Empty
	}

}
