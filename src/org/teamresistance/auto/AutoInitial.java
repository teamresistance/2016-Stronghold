package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class AutoInitial extends State 
{
	protected AutoInitial(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onExit(StateTransition e) {
		gotoState("CrossDefense");
	}
}
