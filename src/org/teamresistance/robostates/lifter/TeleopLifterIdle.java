package org.teamresistance.robostates.lifter;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.Robot;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopLifterIdle extends State {

	@Override
	public void onEntry(StateTransition e) {
		if(e.getInitialState() != null) {
			Robot.teleop.exitIdleDrive();
			IO.lifterMotor.set(0.0);
		}
	}

	@Override
	public void update() {
		if(JoystickIO.btnToggleFoot.onButtonPressed()) {
			IO.flipperSolenoid.set(!IO.flipperSolenoid.get());
//			MoveLifter lifter = ((MoveLifter)stateMachine.getState("MoveLifter"));
//			lifter.setReturnState(getName());
//			lifter.setLowerFlipper(true);
//			gotoState("MoveLifter");
		}
		if(JoystickIO.btnToggleLifter.onButtonPressed()) {
			((MoveLifter)stateMachine.getState("MoveLifter")).setReturnState(getName());
			gotoState("MoveLifter");
		}
		if(JoystickIO.btnPortcullis.isDown() && IO.flipperSolenoid.get()) {
			Robot.teleop.setDriveIdle();
			gotoState("LiftPortcullis");
		} else if(JoystickIO.btnDrawbridge.isDown() && !IO.flipperSolenoid.get()) {
			Robot.teleop.setDriveIdle();
			SmartDashboard.putBoolean("LowerDrawbridge", false);
			gotoState("LowerDrawbridge");
		}
		if(JoystickIO.btnToggleLifterPosition.onButtonPressed()) {
			IO.lifterTiltSolenoid.set(!IO.lifterTiltSolenoid.get());
		}
	}

}
