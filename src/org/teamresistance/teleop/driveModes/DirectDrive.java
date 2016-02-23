package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.StateTransition;

public class DirectDrive extends DriveTrain {

	public DirectDrive(AngleMatch target) {
		super(target);
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
		if(JoystickIO.btnDriveMode.isDown()) {
			gotoState("ScaledDrive");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		//Empty
	}

}
