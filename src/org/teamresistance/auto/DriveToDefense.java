package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DriveToDefense extends State {

	private static final double DRIVE_TIME = 1.25;
	private static final double DRIVE_SPEED = 0.65;

	private double startTime;
	private boolean isReversed;
	
	public DriveToDefense(boolean isReversed) {
		this.isReversed = isReversed;
	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		SmartDashboard.putString("^^^^^^^^^CURRENT STATE:", getName());
	}

	@Override
	public void update() {
		if (Time.getTime() - startTime < DRIVE_TIME) {
			IO.robotDrive.arcadeDrive(isReversed? -1 * DRIVE_SPEED : DRIVE_SPEED, 0);
		} else {
			gotoState("CrossDefense");
		}
	}
}
