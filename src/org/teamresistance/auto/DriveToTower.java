
package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.util.Time;

public class DriveToTower extends State {

	final private static int[][] START_ANGLES = {
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0}
	};
	final private static double[][] DISTANCES = {
		{0.0, 0.0, 0.0, 0.0},
		{0.0, 0.0, 0.0, 0.0},
		{0.0, 0.0, 0.0, 0.0},
		{0.0, 0.0, 0.0, 0.0}
	};
	final private static int[][] END_ANGLES = {
		{0, 0, 0, 0}, 
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0}
	};
	
	private double distance;
	private int startAngle;
	private int endAngle;
	private boolean orientation = false;
	private double elapsed = 0;
	private double speed;
	private boolean turned = false;
	
	public DriveToTower(int defense, int goal) {
		distance = DISTANCES[defense - 2][goal];
		startAngle = START_ANGLES[defense - 2][goal];
		endAngle = END_ANGLES[defense-2][goal];
		speed = AutoConstants.COURTYARD_SPEED;
		
		if(orientation) {
			
			//add 180 and wrap to -180
			startAngle += 180;
			wrap(startAngle);
			endAngle += 180;
			wrap(endAngle);
			
		}
		
		IO.navX.turnTo(startAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
		
	}
	
	public void wrap(double angle) {
		speed*= -1;
		if(angle>180) {
			double difference = angle-180;
			angle = -180+difference;
		}
	}

	@Override
	public void onEntry(StateTransition e) {

	}

	@Override
	public void update() {
		elapsed += Time.getDelta();
		if(elapsed<distance) {
			IO.robotDrive.arcadeDrive(speed,0.0);
		} else {
			IO.navX.turnTo(endAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
			//need to figure out some way of making it go to a targeting state rather than looping
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
