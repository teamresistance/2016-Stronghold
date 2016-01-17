package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class ScaledDrive extends State {

	protected ScaledDrive(StateMachine stateMachine) {
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
		IO.robotDrive.tankDrive(Util.scaleJoytick(IO.leftJoystick.getY()), Util.scaleJoytick(IO.rightJoystick.getY()));
		if(IO.leftJoystick.getRawButton(3)) {
			gotoState("DirectDrive");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		//Empty
	}

}
