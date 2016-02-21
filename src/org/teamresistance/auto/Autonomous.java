package org.teamresistance.auto;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.teamresistance.IO;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	//private StateMachine autoMachine;

	@Override
	public void update() {
		//This is consistantly geting the time of operation
		double currentTime += Time.getDelta();
		(double) SmartDashboard.getNumber("currentTime")
	}
	
	public Autonomous() {
		/*autoMachine = new StateMachine();		
		int defensePosition = 2;
		(int) SmartDashboard.getNumber("defense position");
		int defenseType = 5;
		(int) SmartDashboard.getNumber("defense type");
		int goalPosition = 1;
		(int) SmartDashboard.getNumber("goal");*/
		
		//Keeping code under this for reference	
		//autoMachine.addState(new DriveToTower(defensePosition, goalPosition, defenseType), "DriveToTower");
		//autoMachine1.0.addState(new driveToTower(driveFoward, turnLeft, driveToTower), "driveToTower")
				
		
		// These are the finish time for the tasks to finish
		// This is in seconds
		public void double forwardTime1 = 10;
		public void double turnLeftTime = 15;
		public void double forwardTime2 = 20;
		
		//These are motor power values
		public void double fowardPower1 = 0.5;
		public void double turnLeftPower = 0.25;
		public void double fowardPower2 = 0.5;
		public void double stopPower = 0;
		
		//Rotational Values
		public void double rotateValueFoward = 0;
		public void double rotateValueCounterClockwise = -90;
		
		//This sets the event of operation instead of a switch
		//I used it because I am familiar with using this and time in conjunction
		if (currentTime < fowardTime1){
			//First task
			public void arcadeDrive(fowardPower1, rotateValue);
		} 
		else if ( forwardTime1 <= currentTime < turnLeftTime){
			//Second Task
			public void arcadeDrive(turnLeftPower, rotateValueCounterClockwise);
		} 
		else if (turnLeftTime <= currentTime < forwardTime2){
			//Third Task
			public void arcadeDrive(fowardPower2, rotateValue);			
		} 
		else {
			//When done stop moving
			public void arcadeDrive(stopPower, rotateValue);	
		}
		
		
	}

	@Override
	public void onEntry(StateTransition e) {
		//autautoMachine1.0.setState("driveToTower");
	}

	@Override
	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		
		//IO.imu.turnTo(90, 5);
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
