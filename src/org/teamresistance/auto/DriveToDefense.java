package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveToDefense extends State {

	final private static double[] DISTANCES = {
		
	};
	
	private double elapsedTime;
	
	
	public DriveToDefense(int type) {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		
	}

	@Override
	public void update() {
		if (elapsedTime < 2) {
			IO.robotDrive.arcadeDrive(AutoConstants.COURTYARD_SPEED, 0);
		} else {
			gotoState("CrossDefense");
		}
	}
}
