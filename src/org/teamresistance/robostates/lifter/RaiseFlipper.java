package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.Robot;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class RaiseFlipper extends State {

	private double startTime;

	@Override
	public void onEntry(StateTransition e) {
		IO.flipperSolenoid.set(false);
		startTime = Time.getTime();
	}

	@Override
	public void update() {
		if(Time.getTime() - startTime >= Constants.PORTCULLIS_LIFT_DRIVE_DELAY) {
			IO.robotDrive.arcadeDrive(Constants.PORTCULLIS_DRIVE_SPEED, 0);
		}
		if(IO.bottomFlipperSwitch.get()) {
			if(Robot.robotState.equals("teleop")) {
				((ReturnState)stateMachine.getState("MoveLifter")).setReturnState("LeavePortcullis");
			} else {
				//Set auto return state
				//((MoveLifterDown)stateMachine.getState("MoveLifterUp")).setReturnState("TeleopLifterIdle");
			}
			gotoState("MoveLifter");
		} else if(JoystickIO.btnCancel.isDown()) {
			gotoState("TeleopLifterIdle");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.robotDrive.arcadeDrive(0,0);
	}

}
