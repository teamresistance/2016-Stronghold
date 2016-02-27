package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveToDefense extends State {

	private double elapsedTime;

	@Override
	public void onEntry(StateTransition e) {
		//
	}

	@Override
	public void update() {
		elapsedTime += Time.getDelta();
		if (elapsedTime < 2) {
			IO.robotDrive.arcadeDrive(AutoConstants.COURTYARD_SPEED, 0);
		} else {
			gotoState("CrossDefense");
		}
	}
}
