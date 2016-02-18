package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	private StateMachine autoMachine;
	
	public Autonomous() {
		autoMachine = new StateMachine();
		
		//This may not be done properly, so if the program isn't properly finding the defense position/number/goal this is why
		int defensePosition = (int) SmartDashboard.getNumber("defense position");
		int defenseType = (int) SmartDashboard.getNumber("defense type");
		int goalPosition = (int) SmartDashboard.getNumber("goal");
		
		autoMachine.addState(new CrossDefense(defenseType));
		autoMachine.addState(new DriveToTower(defensePosition, goalPosition));
	}

	@Override
	public void onEntry(StateTransition e) {
		autoMachine.setState("CrossDefense");
	}

	@Override
	public void update() {
		autoMachine.update();
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
