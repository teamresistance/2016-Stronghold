package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	//follows the same indexing as the array found in CrossDefense's constructor
	
	private StateMachine autoMachine;
	
	public Autonomous(StateMachine antlerMachine, StateMachine lifterMachine) {
		autoMachine = new StateMachine();
		
		//This may not be done properly, so if the program isn't properly finding the defense position/number/goal this is why
		int defensePosition = 2;//(int) SmartDashboard.getNumber("defense position");
		int defenseType = 5;//(int) SmartDashboard.getNumber("defense type");
		int goalPosition = 1;//(int) SmartDashboard.getNumber("goal");
		
		//autoMachine.addState(new CrossDefense(defenseType, antlerMachine, lifterMachine), "CrossDefense");
		autoMachine.addState(new DriveToTower(defensePosition, goalPosition, defenseType), "DriveToTower");
	}

	@Override
	public void onEntry(StateTransition e) {
		//autoMachine.setState("CrossDefense");
		autoMachine.setState("DriveToTower");
	
	}

	@Override
	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		
		//if(!IO.imu.isStraight(10, 90)) {
		//	IO.imu.turnTo(90, 10);
		//}
		
		autoMachine.update();
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
