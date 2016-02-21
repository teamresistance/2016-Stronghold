package org.teamresistance.auto.defense;

import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.Constants;
import org.teamresistance.IO;

public class DefenseDrawbridge extends Defense {

	private double startTime;

	@Override
	public void beginCrossing() {
		//startTime = Time.getTime();
		//IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_DRIVE_TRHOUGH_SPEED, 0);
	}

	@Override
	public void whileCrossing() {
		/*if(Time.getTime() - startTime >= Constants.DRAWBRIDGE_FLIPPER_DELAY) {
			IO.flipperSolenoid.set(false);
		}
		if(Time.getTime() - startTime >= Constants.DRAWBRIDGE_DRIVE_THROUGH_TIME) {
			IO.robotDrive.arcadeDrive(0.0, 0.0);
			gotoState("TeleopLifterIdle");
		} else {
			IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_DRIVE_TRHOUGH_SPEED, 0);
		}
	}*/
	}
}
