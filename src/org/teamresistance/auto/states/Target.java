package org.teamresistance.auto.states;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.Util;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Target extends ReturnState {

	private StateMachine driveModes;
	private NetworkTable contoursTable;
	
	public static final int screenWidth = 320; // frame width?
	
	private double kP = 0.5;
	private double targetAngle;

	@Override
	public void onEntry(StateTransition e) {
		targetAngle = IO.imu.getYaw();
	}

	@Override
	public void update() {
		if(!JoystickIO.btnScore.isDown()) {
			gotoReturnState();
		}
		
		double error = targetAngle - IO.imu.getYaw();
		if(Math.abs(error) < 1) {
			error = 0.0;
		}
		double result = error * kP;
		result = Util.clip(result, -1, 1);
		
		int maxIndex = -1;
		double maxArea = -1;
		double[] areas = contoursTable.getNumberArray("area", new double[0]);
		double[] centers = contoursTable.getNumberArray("centerX", new double[0]);
		if(centers.length != areas.length) return;
		for(int i = 0; i < areas.length; i++) {
			if(areas[i] > maxArea) {
				maxIndex = i;
				maxArea = areas[i];
			}
		}
		SmartDashboard.putNumber("Max", maxArea);
		if(maxIndex != -1) {
			double centerX = centers[maxIndex];
			SmartDashboard.putNumber("CenterX Raw", centerX);
			centerX /= screenWidth / 2;
			centerX -= 1;
			SmartDashboard.putNumber("CenterX", centerX);
			if(Math.abs(centerX) < 0.08){
				IO.robotDrive.arcadeDrive(0, 0);
				gotoState("Shoot");
			} else {
				double speed = centerX*-1;
				speed = Util.clip(speed, Constants.TARGET_MIN_SPEED, Constants.TARGET_MAX_SPEED);
				SmartDashboard.putNumber("Speed", speed);
				
				IO.robotDrive.arcadeDrive(speed, result);
			}
		}
	}
	
	public void onExit(StateTransition e) {
		
	}

}