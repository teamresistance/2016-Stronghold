package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AngleMatch extends State {

	private float targetAngle = 0.0f;
	private float angleDeadband = 5.0f;
	private float angleGain = 0.05f; //0.5f

	private String previousStateName = null;
	
	public AngleMatch() {
		
	}
	
	@Override
	public void init() {
		/*
		SmartDashboard.putNumber("TargetAngle", targetAngle);
		SmartDashboard.putNumber("AngleDeadband", angleDeadband);
		SmartDashboard.putNumber("AngleGain", angleGain);
		*/
//		SmartDashboard.putNumber("IntegralGain", angleIntegralGain);
	}

	@Override
	public void onEntry(StateTransition e) {
		previousStateName = e.getInitialState().getName();
	}

	@Override
	public void update() {
		if(!JoystickIO.btnScore.isDown()) {
			gotoState("ScaledDrive");
		}
		
		//targetAngle = (float) Math.toRadians(SmartDashboard.getNumber("TargetAngle"));
//		angleDeadband = (float) Math.toRadians(SmartDashboard.getNumber("AngleDeadband"));
//		angleGain = (float) SmartDashboard.getNumber("AngleGain");
		
		float currentAngle = getCurrentAngle();
		float error = targetAngle - currentAngle;
		SmartDashboard.putNumber("Error", error);
		
		if(Math.abs(error) > 200.0f) {
			if(error > 0) {
				error -= 360.0f;
			} else {
				error += 360.0f;
			}
		}
		
		if(Math.abs(error) < angleDeadband) {
			error = 0.0f;
			gotoState("Target");
		}
		
//		SmartDashboard.putNumber("Result", Util.clip((double)(error*angleGain), -0.7, 0.7));
//		SmartDashboard.putNumber("Result", angleGain*error/Math.abs(error));
//		IO.robotDrive.arcadeDrive(0.0, Util.clip((double)(error*angleGain), -0.7, 0.7));
//		IO.robotDrive.arcadeDrive(0.0, );
//		double speed = angleGain*error/Math.abs(error);
		double speed = Util.clip((double)(error*angleGain), -0.25, 0.25);


		SmartDashboard.putNumber("Turn Speed", speed);
		IO.robotDrive.tankDrive(-speed, 0);
	}

	private float getCurrentAngle() {
		return (float)IO.imu.getYaw();
	}

	public void setTargetAngle(float angle) {
		targetAngle = (float)angle;
	}
	
	public void setAngleDeadband(float angleDeadband) {
		angleDeadband = (float)angleDeadband;
	}

	public void setAngleGain(float angleGain) {
		this.angleGain = angleGain;
	}	
}
