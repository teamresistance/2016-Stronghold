
package org.teamresistance.auto;

import org.teamresistance.Constants;
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
	
	private double distance;
	private int endAngle;
	private double elapsed = 0;
	private double speed;
	
	public DriveToTower(int position, int goal, boolean orient) {
		speed = AutoConstants.COURTYARD_SPEED;
		distance = DISTANCES[position - 2][goal];
		
		int startAngle = 0;
		if(orient) {
			speed *= -1;
			startAngle = 180;
		}
		endAngle = startAngle + ROTATIONS[position - 2][goal];
		
		IO.imu.turnTo(startAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
		
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
