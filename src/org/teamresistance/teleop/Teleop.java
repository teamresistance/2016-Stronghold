package org.teamresistance.teleop;

import org.teamresistance.teleop.driveModes.DirectDrive;
import org.teamresistance.teleop.driveModes.ScaledDrive;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class Teleop extends State {
	
	private StateMachine driveModes;
	
	protected Teleop(StateMachine stateMachine) {
		super(stateMachine);
	}

	@Override
	public void init() {
		driveModes = new StateMachine();
		driveModes.addState(ScaledDrive.class, "ScaledDrive");
		driveModes.addState(DirectDrive.class, "DirectDrive");
	}

	@Override
	public void onEntry(StateTransition e) {
		driveModes.setState("ScaledDrive");
	}

	@Override
	public void update() {
		driveModes.update();
	}

	@Override
	public void onExit(StateTransition e) {
		
	}
	
}
