package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.Relay;

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {

	 // Follows the same indexing as the array found in CrossDefense's constructor
	private final static boolean[] IS_REVERSED = 
		{false, true, false, true, false, true, false};
	
	private final StateMachine autoMachine;

	public Autonomous(StateMachine lifterMachine) {
		autoMachine = new StateMachine();
		
		// This may not be done properly, so if the program isn't properly finding the defense position/number/goal this is why
		int defensePosition = 5;
		int defenseType = 6;
		int goalPosition = 1;
		//(int) SmartDashboard.getNumber("defense position");
		//(int) SmartDashboard.getNumber("defense type");
		//(int) SmartDashboard.getNumber("goal");
		
		autoMachine.addState(new DriveToDefense(defenseType), "DriveToDefense");
		autoMachine.addState(new CrossDefense(defenseType, lifterMachine), "CrossDefense");
		autoMachine.addState(new DriveToTower(defensePosition, goalPosition, IS_REVERSED[defenseType]), "DriveToTower");
	}

	@Override
	public void onEntry(StateTransition e) {
		autoMachine.setState("DriveToDefense");
	}

	@Override
	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);

		//IO.imu.turnTo(90, 5);
		IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		autoMachine.update();
	}

}
