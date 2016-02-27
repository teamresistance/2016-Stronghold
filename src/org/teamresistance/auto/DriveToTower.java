
package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToTower extends State {

	final private static int[][] ROTATIONS = {
		{0, 	50, 	0},
		{-18, 	16, 	0},
		{0, 	-16, 	18},
		{0, 	-50,	0}
	};
	final private static double[][] DISTANCES = {
		{11.5, 	10.4, 	0.0},
		{12.1, 	8.3, 	0.0},
		{0.0, 	8.3, 	12.1},
		{0.0, 	10.4, 	11.5}
	};
	
	private double driveTime = 2;
	private int angle;
	private double startTime;
	private double speed;
	
	private boolean atAngle = false;
	
	public DriveToTower(int defense, int goal, boolean isReversed) {
		speed = AutoConstants.COURTYARD_SPEED;
		//driveTime = DISTANCES[defense - 2][goal] / 4; //4 ft / s?
		angle = ROTATIONS[defense - 2][goal];
		
		if(isReversed) {
			speed *= -1;
		}
	}
	
	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		angle = -40;
	}

	@Override
	public void update() {
		SmartDashboard.putBoolean("*************DRIVETOTOWER ENTERED", false);
		if(!atAngle) {
			if(Math.abs(IO.imu.getYaw() - angle) < 5) {
				atAngle = true;
				startTime = Time.getTime();
			} else {
				if(angle < 0) {
					IO.robotDrive.tankDrive(0.5, -0.5);
				} else {
					IO.robotDrive.tankDrive(-0.5, 0.5);
				}
			}
		} else {
			if(Time.getTime() - startTime < driveTime) {
				IO.robotDrive.arcadeDrive(-0.6, 0.0);
			} else {
				//target!
				IO.robotDrive.arcadeDrive(0, 0);
				SmartDashboard.putBoolean("asdouafshjaf", false);
			}
		}
	}

}
