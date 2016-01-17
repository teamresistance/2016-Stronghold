package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class DirectDrive extends State {

	protected DirectDrive(StateMachine stateMachine) {
		super(stateMachine);
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
		IO.robotDrive.tankDrive(IO.leftJoystick.getY(), IO.rightJoystick.getY());
		if(IO.leftJoystick.getRawButton(3)) {
			gotoState("ScaledDrive");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		//Empty
	}

}
