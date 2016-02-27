
package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToTower extends State {
	
	private double driveTime = 2; // only referenced in constructor
	private int angle;
	private double startTime;
	private double speed; // only referenced in constructor

	private boolean atAngle = false;

	public DriveToTower(Defense fromDefense, int defensePosition, int goal) {
		speed = AutoConstants.COURTYARD_SPEED;
		//driveTime = DISTANCES[defense - 2][goal] / 4; //4 ft / s?
		//angle = ROTATIONS[defensePosition - 2][goal];

		if(fromDefense.isReversed()) {
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
