package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class AngleHold extends ReturnState {

	private double targetAngle;

	@Override
	public void onEntry(StateTransition e) {
		targetAngle = Math.toRadians(IO.imu.getYaw());
	}

	@Override
	public void update() {
		if(!JoystickIO.btnAngleHold.isDown()){
			gotoReturnState();
		}
		double currentAngle = Math.toRadians(IO.imu.getYaw());
		double rotateSpeed = calculateAngleCorrection(currentAngle, targetAngle);
		double moveSpeed = Util.scaleJoytick(JoystickIO.rightJoystick.getY());
		IO.robotDrive.arcadeDrive(moveSpeed, rotateSpeed);
	}

	/**
	 * Calculates the speed the robot needs to turn in order to maintain the desired angle.
	 * @param currentAngle the given angle, in radians
	 * @param desiredAngle the angle to hold, in radians
     * @return the optimal rotation speed for correcting the angle error
     */
	public static double calculateAngleCorrection(double currentAngle, double desiredAngle) {
		double error = desiredAngle - currentAngle;

		// If our error is too large, calculate the rotation speed needed to correct it
		return Math.abs(error) > 0.017 ? Util.clip(error * 4.0, -1.0, 1.0) : 0;
	}

}
