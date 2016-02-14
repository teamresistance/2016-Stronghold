package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class Autonomous extends State {

	/* Need to do: 
	 * Take in info about which defense/which position it's crossing
	 * Cross that defense type
	 * Choose which goal to shoot at
	 * Go to that goal
	 * Line up
	 * Shoot
	 */
	
	protected Autonomous(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		//needs to pull from DefenseTypePositionSelector.java in order to determine what defense it's going for, then pull from AutonomousDefenses.java to actually execute the motion
	}

	@Override
	public void onEntry(StateTransition e) {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
