
package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveToTower extends State {

	final private static int[][] ROTATIONS = {
		{0, 	-40, 	0},
		{18, 	-16, 	0},
		{0, 	16, 	-18},
		{0, 	40,		0}
	};
	final private static double[][] DISTANCES = {
		{11.5, 	10.4, 	0.0},
		{12.1, 	8.3, 	0.0},
		{0.0, 	8.3, 	12.1},
		{0.0, 	10.4, 	11.5}
	};
	
	private double driveTime;
	private int angle;
	private double elapsedTime;
	private double speed;
	
	public DriveToTower(int defense, int goal, boolean isReversed) {
		speed = AutoConstants.COURTYARD_SPEED;
		driveTime = DISTANCES[defense - 2][goal] / 4; //4 ft / s?
		angle = ROTATIONS[defense - 2][goal];
		elapsedTime = 0;
		
		if(isReversed) {
			speed *= -1;
		}
	}
	
	@Override
	public void onEntry(StateTransition e) {
		IO.imu.turnTo(angle, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}

	@Override
	public void update() {
		elapsedTime += Time.getDelta();
		if(elapsedTime < driveTime) {
			IO.robotDrive.arcadeDrive(speed, 0.0);
		} else {
			//target!
		}
	}

}
