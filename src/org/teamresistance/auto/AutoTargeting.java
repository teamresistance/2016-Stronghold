package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.JoystickIO;
import org.teamresistance.teleop.driveModes.Target;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTargeting extends State{
	
	private static float targetAngle = 0.0f;
	private static float angleDeadband = 5.0f;
	private static float angleGain = 0.5f;

	@Override
	public void init() {
		/*
		SmartDashboard.putNumber("TargetAngle", targetAngle);
		SmartDashboard.putNumber("AngleDeadband", angleDeadband);
		SmartDashboard.putNumber("AngleGain", angleGain);
		*/
		
	}

	@Override
	public void onEntry(StateTransition e) {

	}

	@Override
	public void update() {
		angleDeadband = (float) Math.toRadians(SmartDashboard.getNumber("AngleDeadband"));
		angleGain = (float) SmartDashboard.getNumber("AngleGain");
		
		float currentAngle = getCurrentAngle();
		float error = targetAngle - currentAngle;
		SmartDashboard.putNumber("Error", error);
		/*
		if(Math.abs(error/(2 * Math.PI)) > 0.8) {
			if(error > 0) {
				error -= (float)(2 * Math.PI);
			} else {
				error += (float)(2 * Math.PI);
			}
		}
		*/
		if(Math.abs(error) < angleDeadband) {
			error = 0.0f;
		}
		
		SmartDashboard.putNumber("Result", Util.clip((double)(error*angleGain), -1.0, 1.0));
		IO.robotDrive.arcadeDrive(0.0, Util.clip((double)(error*angleGain), -1.0, 1.0));
		
		
	}

	@Override
	public void onExit(StateTransition e) {
			gotoState("Targeting");
	}
	


private float getCurrentAngle() {
	return (float)Math.toRadians(IO.imu.getYaw());
}

public static void setTargetAngle(float angle) {
	AutoTargeting.targetAngle = (float)Math.toRadians(angle);
}

public static void setAngleDeadband(float angleDeadband) {
	AutoTargeting.angleDeadband = (float)Math.toRadians(angleDeadband);
}

public static void setAngleGain(float angleGain) {
	AutoTargeting.angleGain = angleGain;
}	
}

