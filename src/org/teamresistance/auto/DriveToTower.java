
package org.teamresistance.auto;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortcullis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockWall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.util.Time;

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
	
	//	Cheval, Drawbridge, Moat, Portcullis, Ramparts, RockWall, Rough terrain
	final private static boolean[] ORIENTATION = 
		{false, true, false, true, false, true, false};
	
	
	private double distance;
	private int startAngle;
	private int endAngle;
	private double elapsed = 0;
	private double speed;
	private boolean turned = false;
	private boolean orient;
	
	public DriveToTower(int position, int goal, int defense) {
		distance = DISTANCES[position-2][goal];
		startAngle = START_ANGLES[position=2][goal];
		endAngle = END_ANGLES[position-2][goal];
		speed = AutoConstants.COURTYARD_SPEED;
		orient = ORIENTATION[defense];
		
		if(orient) {
			
			//add 180 and wrap to -180
			startAngle += 180;
			wrap(startAngle);
			endAngle += 180;
			wrap(endAngle);
			
		}
		
		IO.imu.turnTo(startAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
		
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

	@Override
	public void onExit(StateTransition e) {
		
	}

}
