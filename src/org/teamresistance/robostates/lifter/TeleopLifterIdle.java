package org.teamresistance.robostates.lifter;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.Robot;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class TeleopLifterIdle extends State {

	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		if(e.getInitialState() != null)
			Robot.teleop.exitIdleDrive();
	}

	@Override
	public void update() {
		if(JoystickIO.btnToggleFoot.onButtonPressed()) {
			IO.flipperSolenoid.toggle();
			if(!IO.bottomLifterSwitch.get()) {
				((MoveLifter)stateMachine.getState("MoveLifter")).setReturnState(getName());
				gotoState("MoveLifter");
			}
		} else if(JoystickIO.btnToggleLifter.onButtonPressed()) {
			((MoveLifter)stateMachine.getState("MoveLifter")).setReturnState(getName());
			gotoState("MoveLifter");
		} else if(JoystickIO.btnPortcullis.isDown()) {
			Robot.teleop.setDriveIdle();
			gotoState("LiftPortcullis");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
