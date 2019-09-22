package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.AngleHold;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DriveToDefense extends State {

	private static final double DRIVE_TIME = 1.0;
	private static final double DRIVE_SPEED = -0.65;

	private double startTime;
	private boolean isReversed;
	private boolean isDefenseCheval;
	
	public DriveToDefense(boolean isReversed, boolean isDefenseCheval) {
		this.isReversed = isReversed;
		this.isDefenseCheval = isDefenseCheval;
	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
	}

	@Override
	public void update() {
		if (isDefenseCheval) {
			IO.antlerSolenoid.set(true);
		}
		
		if (Time.getTime() - startTime < DRIVE_TIME) {
            double driveSpeed = isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED;
            IO.robotDrive.arcadeDrive(driveSpeed, 0);
		} else {
			gotoState("CrossDefense");
		}
	}
}
