package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

public class AngleHold extends ReturnState {

	private double targetAngle;
	private double kP = 4;

	public AngleHold() {
		//SmartDashboard.putNumber("Angle Hold kP", 0.5);
	}

	@Override
	public void onEntry(StateTransition e) {
		targetAngle = Math.toRadians(IO.imu.getYaw());
	}

	@Override
	public void update() {
		if(!JoystickIO.btnAngleHold.isDown()){
			gotoReturnState();
		}
		//kP = SmartDashboard.getNumber("Angle Hold kP", 0.5);
		double error = targetAngle - Math.toRadians(IO.imu.getYaw());
		if(Math.abs(error) < 0.017) {
			error = 0;
		}
		double result = error * kP;
		result = Util.clip(result, -1.0, 1.0);
		IO.robotDrive.arcadeDrive(Util.scaleJoytick(JoystickIO.rightJoystick.getY()), result);
	}

}
