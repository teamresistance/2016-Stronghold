package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortculllis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockwall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.command.Command;



 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	//private StateMachine autoMachine;
	
	//This defines parts that gets smartdashboard input
	Command autonomousCommand;
	SendableChooser defenseChooser;

	@Override
	public void update() {
	}
	
	public Autonomous() {
		/*int defensePosition = 2;
		*(int) SmartDashboard.getNumber("defense position");
		*int defenseType = 5;
		*(int) SmartDashboard.getNumber("defense type");
		i*nt goalPosition = 1;
		*(int) SmartDashboard.getNumber("goal");
		*/
		
		//This is were you choose the base that is infront of the robot		
		defenseChooser = SendableChooser();
		defenseChooser.addObject("TeeterTater", DefenseCheval());
		defenseChooser.addObject("Moat", DefenseMoat());
		defenseChooser.addObject("Drawbridge", DefenseDrawbridge());
		defenseChooser.addObject("Portculllis", DefensePortculllis());
		defenseChooser.addObject("Ramparts", DefenseRamparts());
		defenseChooser.addObject("Rockwall", DefenseRockwall());
		defenseChooser.addObject("Rough Terrain", DefenseRoughTerrain());
		SmartDashboard.putData("Automous chooser", defenseChooser);
		
		//These are for angle matching
		//They are not specifically ints, or doubles or anything
		int position;
		boolean orient;
		double angle;
		int endAngle;
		
		//This is times for moving places
		double orientTime = 10;
		double driveToDefenseTime = 20;
		double crossDefenseTime = 30;
		double moveForShotTime = 40;
		double shootTime = 5;
				
		enum State {getOriented, driveToDefense, crossDefense, moveForShot, shoot, done};
		State autoStates;
		
		autoStates = State.orient;

		distance = DISTANCES[position-2][goal];
		int startAngle = START_ANGLES[position-2][goal];
		endAngle = END_ANGLES[position-2][goal];
		speed = AutoConstants.COURTYARD_SPEED;
		
		switch(autoStates) {
		case getOriented:
			
			if(orient) {
				
				//add 180 and wrap to -180
				startAngle += 180;
				wrap(startAngle);
				endAngle += 180;
				wrap(endAngle);
				
			}
			break;
			
			if (currentTime >= fowardTime1){
				autoStates = State.driveToDefense;
			}
		
		case driveToDefense:
			if (currentTime >= driveToDefenseTime){
				autoStates = State.crossDefense;
			}
			break;
		
		case crossDefense:
			
			//This is how to excute the defense chose
			autonomousCommand = (Command) defenseChooser.getselected;
			autonomousCommand.start();
			 
			if (currentTime >= crossDefenseTime){
				autoStates = State.moveForShot;
			}
			break;
		
		case moveForShot:
			if (currentTime >= moveForShotTime){
				autoStates = State.shoot;
			}
		
		case shoot:
			if (currentTime >= shootTime){
				autoStates = State.done;
			}
			break;
			
		case done:
			
			break;
			
		}

	@Override
	private void wrap(double angle) {
		speed*= -1;
		if(angle>180) {
			double difference = angle-180;
			angle = -180+difference;
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

		//This is consistantly geting the time of operation
		double currentTime += Time.getDelta();
		SmartDashboard.putNumber("Time", currentTime);

		if(currentTime<distance) {
			IO.robotDrive.arcadeDrive(speed,0.0);
		}
			
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
