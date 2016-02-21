
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
import org.teamresistance.auto.AutoConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToTower extends State {
	
	//	Cheval, Drawbridge, Moat, Portcullis, Ramparts, RockWall, Rough terrain
	final private static boolean[] ORIENTATION = 
		{false, true, false, true, false, true, false};
	
	
	private double distance;
	private int startAngle = 90;
	private int endAngle;
	private double elapsed = 0;
	private double speed;
	private boolean turned = false;
	private boolean orient;
	
	public DriveToTower(int position, int goal, int defense) {
		distance = AutoConstants.DISTANCES[position-2][goal];
		SmartDashboard.putNumber("Travel time", distance);
		startAngle = AutoConstants.START_ANGLES[position-2][goal];
		//SmartDashboard.putNumber("angle", startAngle);
		endAngle = AutoConstants.END_ANGLES[position-2][goal];
		speed = AutoConstants.COURTYARD_SPEED;
		SmartDashboard.putNumber("Speed: ", speed);
		orient = ORIENTATION[defense];
		
		/*if(orient) {
			
			//add 180 and wrap to -180
			startAngle += 180;
			wrap(startAngle);
			endAngle += 180;
			wrap(endAngle);
			
		}*/
		
		//IO.imu.turnTo(startAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
		SmartDashboard.putNumber("Angle", startAngle);
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

	private boolean firstTurn = false;
	private boolean driven = false;
	private boolean lastTurn = false;
	private double time = -1;
	boolean first = true;
	@Override
	public void update() {
		elapsed += Time.getDelta();
		SmartDashboard.putNumber("Elapsed Time", elapsed);
		SmartDashboard.putNumber("startAngle = ",startAngle);
		SmartDashboard.putNumber("Time", time);
		if(firstTurn == false) {
			firstTurn = turn(40);
			time = elapsed;
		
		}else {
			if((elapsed-time)<=distance && driven == false) {
				drive();
			} else {
				driven = true;
				if(lastTurn == false && driven == true) {
					lastTurn = turn(0);
				}
			}
		}
	}
				
		//else {
			//IO.imu.turnTo(endAngle, AutoConstants.ANGLE_ERROR_THRESHOLD);
			//need to figure out some way of making it go to a targeting state rather than looping
			//while(IO.snorflerSolenoid.get()) {
			//	IO.snorflerSolenoid.set(false);
			//}
			//while(IO.ballSensor.get()) {
			//	IO.snorflerMotor.set(Constants.SNORFLE_DUMP_SPEED);
			//}
			
		//}
	//}

	public void drive() {
		IO.robotDrive.arcadeDrive(speed,0.0);
	}
	
	public boolean turn(int angle) {
		SmartDashboard.putNumber("Current Angle", IO.imu.getYaw());
	
		if(!IO.imu.isStraight(AutoConstants.ANGLE_ERROR_THRESHOLD, angle)) {
			
				IO.imu.turnTo(angle, AutoConstants.ANGLE_ERROR_THRESHOLD);
				return false;
		}else {
			return true;
		}
	}
	
	@Override
	public void onExit(StateTransition e) {
		
	}

}
