
package org.teamresistance.auto;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveToTower extends State {

	final private static int[][] START_ANGLES = {
		{0, 30, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0}
	};
	final private static double[][] DISTANCES = {
		{1.0, 0.0, 0.0, 0.0},
		{0.0, 0.0, 0.0, 0.0},
		{0.0, 0.0, 0.0, 0.0},
		{0.0, 0.0, 0.0, 0.0}
	};
	final private static int[][] END_ANGLES = {
		{0, -60, 0, 0}, 
		{0, 0, 0, 0},
		{0, 0, 0, 0},
		{0, 0, 0, 0}
	};
	
	private double distance;
	private int endAngle;
	private double elapsed = 0;
	private double speed;
	
	public DriveToTower(int position, int goal, boolean orient) {
		distance = DISTANCES[position-2][goal];
		int startAngle = START_ANGLES[position-2][goal];
		endAngle = END_ANGLES[position-2][goal];
		speed = AutoConstants.COURTYARD_SPEED;
		
		if(orient) {
			
			//add 180 and wrap to -180
			startAngle += 180;
			wrap(startAngle);
			endAngle += 180;
			wrap(endAngle);
			
		}
		
		IO.imu.turnTo(startAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
		
	}
	
	private void wrap(double angle) {
		speed*= -1;
		if(angle>180) {
			double difference = angle-180;
			angle = -180+difference; // should that assignment be to a member?
		}
	}

	@Override
	public void onEntry(StateTransition e) {
		//
	}

	@Override
	public void update() {
		elapsed += Time.getDelta();
		if(elapsed<distance) {
			IO.robotDrive.arcadeDrive(speed,0.0);
		} else {
			IO.imu.turnTo(endAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
			//need to figure out some way of making it go to a targeting state rather than looping
			while(IO.snorflerSolenoid.get()) {
				IO.snorflerSolenoid.set(false);
			}
			while(IO.ballSensor.get()) {
				IO.snorflerMotor.set(Constants.SNORFLE_DUMP_SPEED);
			}
			
		}
	}

}
