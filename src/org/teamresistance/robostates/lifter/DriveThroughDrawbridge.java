package org.teamresistance.robostates.lifter;

import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Drive through the Drawbridge after lifting the gate.
 */
public class DriveThroughDrawbridge extends State {

	public static final double DRIVE_TRHOUGH_SPEED = 0.6;
	public static final double DRIVE_THROUGH_TIME = 3.0; // seconds
	public static final double FLIPPER_DELAY_TIME = 1.0; // seconds

	private final RobotDrive robotDrive;
	private final Solenoid flipperSolenoid;

	private double startTime;

	public DriveThroughDrawbridge(RobotDrive robotDrive, Solenoid flipperSolenoid) {
		this.robotDrive = robotDrive;
		this.flipperSolenoid = flipperSolenoid;
	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		robotDrive.arcadeDrive(DRIVE_TRHOUGH_SPEED, 0);
	}

	@Override
	public void update() {
		// After the flippers have lowered, raise them to ease pushing the drawbridge.
		if (Time.getTime() - startTime >= FLIPPER_DELAY_TIME) {
			flipperSolenoid.set(false);
		}

		// If we've crossed the drawbrdige...
		if (Time.getTime() - startTime >= DRIVE_THROUGH_TIME) {
			// Kill the motor and idle the lifter.
			robotDrive.arcadeDrive(0.0, 0.0);
			gotoState("TeleopLifterIdle");
		} else {
			// Otherwise, keep on chuggin'.
			robotDrive.arcadeDrive(DRIVE_TRHOUGH_SPEED, 0);
		}
	}

}
