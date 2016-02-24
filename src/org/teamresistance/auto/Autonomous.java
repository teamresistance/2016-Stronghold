package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortcullis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockWall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Command;


/*
* Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
*/
public class Autonomous extends State {

	final public static double[][] DISTANCES = {
			{0, 30, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0	}
	};

	final public static int[][] START_ANGLES = {
			{0, 30, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
	};

	final public static int[][] END_ANGLES = {
			{0, -60, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
	};

	//private StateMachine autoMachine;
	
	//This defines parts that gets SmartDashboard input
	Command autonomousCommand;
	SendableChooser defenseChooser;

	enum State {getOriented, driveToDefense, crossDefense, moveForShot, shoot, done}

	double distance;
	double speed;
	double currentTime = 0;
	int goal;


	public Autonomous() {
		/*int defensePosition = 2;
		*(int) SmartDashboard.getNumber("defense position");
		*int defenseType = 5;
		*(int) SmartDashboard.getNumber("defense type");
		i*nt goalPosition = 1;
		*(int) SmartDashboard.getNumber("goal");
		*/

		//This is were you choose the base that is in front of the robot
		defenseChooser = new SendableChooser();
		defenseChooser.addObject("Teeter Tater", new  DefenseCheval());
		defenseChooser.addObject("Moat", new DefenseMoat());
		defenseChooser.addObject("Drawbridge", new  DefenseDrawbridge());
		defenseChooser.addObject("Portcullis", new DefensePortcullis());
		defenseChooser.addObject("Ramparts", new DefenseRamparts());
		defenseChooser.addObject("RockWall", new DefenseRockWall());
		defenseChooser.addObject("Rough Terrain", new DefenseRoughTerrain());
		SmartDashboard.putData("Autonomous chooser", defenseChooser);

		//These are for angle matching
		//They are not specifically ints, or doubles or anything
		int position = 0;
		int endAngle;

		//This is times for moving places
		double orientTime = 3;
		double driveToDefenseTime = 6;
		double crossDefenseTime = 9;
		double moveForShotTime = 12;
		double shootTime = 15;

		//This is power for moving
		double driveToDefensePower = 0.5;
		double moveForShotPower = 0.5;


		State autoStates;

		autoStates = State.getOriented;

		//distance = DISTANCES[position - 2][goal];
		//int startAngle = START_ANGLES[position - 2][goal];
		//endAngle = END_ANGLES[position - 2][goal];
		//speed = AutoConstants.COURTYARD_SPEED;

		autonomousCommand = (Command) defenseChooser.getSelected();
		autonomousCommand.start();

//		switch (autoStates) {
//			case getOriented:
//
//				if (currentTime >= orientTime) {
//					autoStates = State.driveToDefense;
//				}
//				break;
//
//			case driveToDefense:
//
//				IO.robotDrive.arcadeDrive(driveToDefensePower,0.0);
//
//				if (currentTime >= driveToDefenseTime) {
//					autoStates = State.crossDefense;
//				}
//				break;
//
//			case crossDefense:
//				//This is how to execute the defense chose
//				autonomousCommand = (Command) defenseChooser.getSelected();
//				autonomousCommand.start();
//
//				if (currentTime >= crossDefenseTime) {
//					autoStates = State.moveForShot;
//				}
//				break;
//
//			case moveForShot:
//
//				IO.robotDrive.arcadeDrive(moveForShotPower,0.5);
//
//				if (currentTime >= moveForShotTime) {
//					autoStates = State.shoot;
//				}
//				break;
//
//			case shoot:
//
//				IO.robotDrive.arcadeDrive(0,0.0);
//
//				if (currentTime >= shootTime) {
//					autoStates = State.done;
//				}
//				break;
//
//			case done:
//				//This should stop the robot
//				IO.robotDrive.arcadeDrive(0,0.0);
//				break;
//
//		}
	}

	private void wrap(double angle) {
		speed*= -1;
		if(angle>180) {
			double difference = angle-180;
			angle = -180+difference;
		}
	}
	
	public void onEntry(StateTransition e) {
		//autoMachine1.0.setState("driveToTower");
	}

	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		//IO.imu.turnTo(90, 5);

		//This is consistently getting the time of operation
		currentTime += Time.getDelta();
		SmartDashboard.putNumber("Time", currentTime);

		if(currentTime<distance) {
			IO.robotDrive.arcadeDrive(speed,0.0);
		}
			
		}


	public void onExit(StateTransition e) {
		
	}

}
