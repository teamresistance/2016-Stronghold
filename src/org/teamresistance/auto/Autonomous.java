package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	//follows the same indexing as the array found in CrossDefense's constructor
	
	private StateMachine autoMachine;
	
	//public Autonomous(StateMachine antlerMachine, StateMachine lifterMachine) {
	public Autonomous() {
		autoMachine = new StateMachine();
		
		//autoMachine.addState(new CrossDefense(defenseType), "CrossDefense");
		//autoMachine.addState(new DriveToTower(defensePosition, goalPosition, (int) defenseType), "DriveToTower");
		
		int defenseType = 4;//(int) SmartDashboard.getNumber("Defense Type");
		int defensePosition = 2;
		int goalPosition = 2;
		
		autoMachine.addState(new DriveToTower(defenseType, goalPosition, defenseType), "DriveToTower");
		
		autoMachine.setState("DriveToTower");
		
	}

	@Override
	public void onEntry(StateTransition e) {
		//autoMachine.setState("CrossDefense");
	
	}

	@Override
	public void update() {
		//double test = SmartDashboard.getNumber("Test");
		//SmartDashboard.putNumber("Defense Type Choice", test);
		
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		
		//if(!IO.imu.isStraight(10, 90)) {
		//	IO.imu.turnTo(90, 10);
		//}
		//SmartDashboard.getData("test number");
		//double test = SmartDashboard.getData("test number");
		//SmartDashboard.putNumber("TestPut", test);
		
		//autoMachine.update(); //comment this in/out to enable movement
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
