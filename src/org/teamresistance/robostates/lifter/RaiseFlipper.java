package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.Robot;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RaiseFlipper extends State {

	@Override
	public void init() {
	
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.flipperSolenoid.set(false);
		
	}

	@Override
	public void update() {
		IO.robotDrive.arcadeDrive(Constants.PORTCULLIS_DRIVE_SPEED, 0);
		if(IO.bottomFlipperSwitch.get()) {
			if(Robot.robotState.equals("teleop")) {
				((ReturnState)stateMachine.getState("MoveLifter")).setReturnState("TeleopLifterIdle");
			} else {
				//Set auto return state
				//((MoveLifterDown)stateMachine.getState("MoveLifterUp")).setReturnState("TeleopLifterIdle");
			}
			gotoState("MoveLifter");
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.robotDrive.arcadeDrive(0,0);
	}

}
