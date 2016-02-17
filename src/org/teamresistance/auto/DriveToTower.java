package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.defenses.DefenseMaster;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToTower extends State {
	
	private static int position;
	private static int goal;
	private static int defense;
	private static boolean portcullis;
	private static double elapsed;
	private Time timer;
	private static double initAngle;
	private static double difference;

	protected DriveToTower(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		SmartDashboard.putNumber("defense position", position);
	}
	
	
	@Override
	public void onEntry(StateTransition e) {
		position = (int) SmartDashboard.getNumber("defense position", position);
		goal = (int) SmartDashboard.getNumber("Goal choice", goal);
		defense = (int) SmartDashboard.getNumber("defense type", defense);
		timer = new Time();
		
		if(defense == 4) portcullis = true;
		
		
		switch(position) {
		//need to consider that the portcullis reverses the initial orientation of the robot
		case 2: if(portcullis) pos2DriveLeftGoalPortcullis(); else pos2DriveLeftGoal();
			break;
		case 3: if(portcullis) pos3DriveMiddleGoalPortcullis(); else pos3DriveMiddleGoal();
			break;
		case 4: if(portcullis) pos4DriveRightGoalPortcullis(); else pos4DriveRightGoal();
			break;
		default: if(portcullis)	pos5DriveRightGoalPortcullis(); else pos5DriveRightGoal();		//position 5
			break;
		}
	}

	@Override
	public void update() {
	//	if(posCorrect()) {
	//		gotoState("Targeting");
	//	}
	}

	@Override
	public void onExit(StateTransition e) {
			gotoState("Targeting");
	}

	/*
	 * Implemented combinations: 
	 * 
	 * Unimplemented combinations (priority): 
	 * position 2/left goal
	 * position 3/middle goal
	 * position 4/right goal
	 * position 5/right goal
	 * 
	 * lower priority: 
	 * position 2/middle goal
	 * position 3/left goal
	 * position 3/right goal
	 * position 4/middle goal
	 * position 5/middle goal
	 * 
	 * Unsupported combinations: 
	 * position 2/right goal
	 * position 4/left goal
	 * position 5/left goal
	 * position 5/middle goal
	 */

	/*public void turnAngle(double angle) {
		initAngle = DefenseMaster.imu.getYawAngle();
		difference = 0;
		IO.robotDrive.arcadeDrive(0,0.5);
		while(difference<angle&&elapsed>0.25&&elapsed<0.5) {
			difference = (initAngle-DefenseMaster.imu.getYawAngle());
			timer.update();
			elapsed = timer.getDelta();
		}
		IO.robotDrive.arcadeDrive(0,0);
	}*/
	
	public void driveForTime(double time) {
		timer.update();
		elapsed = timer.getDelta();
		IO.robotDrive.arcadeDrive(0.5, 0);
		while(elapsed<time){
			timer.update();
			elapsed = timer.getDelta();
		}
		IO.robotDrive.arcadeDrive(0, 0);
	}
	
	public void pos2DriveLeftGoal() {
		/*
		 * turn to the left
		 * drive forward
		 * turn right to approx perpendicular to tower face
		 */
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_LEFT_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_2_LEFT_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_LEFT_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos2DriveLeftGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_LEFT_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_2_LEFT_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_LEFT_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos2DriveMiddleGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_MIDDLE_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_2_MIDDLE_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_MIDDLE_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos2DriveMiddleGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_MIDDLE_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_2_MIDDLE_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_2_MIDDLE_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos3DriveLeftGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_LEFT_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_3_LEFT_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_LEFT_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos3DriveLeftGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_LEFT_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_3_LEFT_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_LEFT_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos3DriveMiddleGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_MIDDLE_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_3_MIDDLE_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_MIDDLE_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos3DriveMiddleGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_MIDDLE_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_3_MIDDLE_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_MIDDLE_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos3DriveRightGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_RIGHT_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_3_RIGHT_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_RIGHT_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos3DriveRightGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_RIGHT_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_3_RIGHT_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_3_RIGHT_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos4DriveMiddleGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_MIDDLE_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_4_MIDDLE_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_MIDDLE_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos4DriveMiddleGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_MIDDLE_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_4_MIDDLE_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_MIDDLE_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos4DriveRightGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_RIGHT_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_4_RIGHT_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_RIGHT_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos4DriveRightGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_RIGHT_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_4_RIGHT_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_4_RIGHT_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos5DriveRightGoal() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_5_RIGHT_START_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_5_RIGHT_DISTANCE_TIME);
		DefenseMaster.imu.turnTo(AutoConstants.POS_5_RIGHT_END_ANGLE, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}
	public void pos5DriveRightGoalPortcullis() {
		DefenseMaster.imu.turnTo(AutoConstants.POS_5_RIGHT_START_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
		driveForTime(AutoConstants.POS_5_RIGHT_DISTANCE_TIME_PORTCULLIS);
		DefenseMaster.imu.turnTo(AutoConstants.POS_5_RIGHT_END_ANGLE_PORTCULLIS, AutoConstants.ANGLE_ERROR_THRESHOLD);
	}

}