package org.teamresistance.auto;

import org.teamresistance.IO;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	//private StateMachine autoMachine;
	
	//This defines parts that gets smartdashboard input
	Command autonomousCommand;
	SendableChooser position chooser

	@Override
	public void update() {
		//This is consistantly geting the time of operation
		double currentTime += Time.getDelta();
		(double) SmartDashboard.getNumber("currentTime")
	}
	
	public Autonomous() {
		/*autoMachine = new StateMachine();		
		*int defensePosition = 2;
		*(int) SmartDashboard.getNumber("defense position");
		*int defenseType = 5;
		*(int) SmartDashboard.getNumber("defense type");
		*int goalPosition = 1;
		*(int) SmartDashboard.getNumber("goal");
		*/
		
		//Keeping code under this for reference	
		//autoMachine.addState(new DriveToTower(defensePosition, goalPosition, defenseType), "DriveToTower");
		//autoMachine1.0.addState(new driveToTower(driveFoward, turnLeft, driveToTower), "driveToTower")
				
		
		
		
		
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
