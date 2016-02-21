package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.Util;

public class ScaledDrive extends DriveTrain {

	public ScaledDrive(AngleMatch target) {
		super(target);
	}

	@Override
	public void update() {
		super.update();
		IO.robotDrive.tankDrive(Util.scaleJoytick(getLeftY()), Util.scaleJoytick(getRightY()));
		if(JoystickIO.btnDriveMode.isDown()) {
			gotoState("DirectDrive");
		}
	}

}
