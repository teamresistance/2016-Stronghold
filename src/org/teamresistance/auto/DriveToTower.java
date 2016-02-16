package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToTower extends State {
	
	private static int position;
	private static int goal;
	private static int defense;
	private static boolean portcullis;

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
	 * Unimplemented combinations: 
	 * position 2/left goal
	 * position 2/middle goal
	 * position 3/left goal
	 * position 3/middle goal
	 * position 3/right goal
	 * position 4/middle goal
	 * position 4/right goal
	 * position 5/right goal
	 * 
	 * Unsupported combinations: 
	 * position 2/right goal
	 * position 4/left goal
	 * position 5/left goal
	 * position 5/middle goal
	 */

	public void pos2DriveLeftGoal() {
		
	}
	public void pos2DriveLeftGoalPortcullis() {
		
	}
	public void pos2DriveMiddleGoal() {
		
	}
	public void pos2DriveMiddleGoalPortcullis() {
	
	}
	public void pos3DriveLeftGoal() {
	
	}
	public void pos3DriveLeftGoalPortcullis() {
		
	}
	public void pos3DriveMiddleGoal() {
		
	}
	public void pos3DriveMiddleGoalPortcullis() {
		
	}
	public void pos3DriveRightGoal() {
		
	}
	public void pos3DriveRightGoalPortcullis() {
		
	}
	public void pos4DriveMiddleGoal() {
		
	}
	public void pos4DriveMiddleGoalPortcullis() {
		
	}
	public void pos4DriveRightGoal() {
		
	}
	public void pos4DriveRightGoalPortcullis() {
		
	}
	public void pos5DriveRightGoal() {
	
	}
	public void pos5DriveRightGoalPortcullis() {
		
	}

}