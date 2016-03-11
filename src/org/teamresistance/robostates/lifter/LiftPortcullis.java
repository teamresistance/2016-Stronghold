package org.teamresistance.robostates.lifter;

import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class LiftPortcullis extends State {

	private final Solenoid lifterTiltSolenoid;
	private final DigitalInput bottomLifterSwitch;

	public LiftPortcullis(Solenoid lifterTiltSolenoid, DigitalInput bottomLifterSwitch) {
		this.lifterTiltSolenoid = lifterTiltSolenoid;
		this.bottomLifterSwitch = bottomLifterSwitch;
	}

	@Override
	public void onEntry(StateTransition e) {
		// Retract the solenoid
		lifterTiltSolenoid.set(false);

		// If the bottom limit switch has not been hit yet...
		if (!bottomLifterSwitch.get()) {
			// Move the lifter down and then reenter this LiftPortcullis state to check again
			((ReturnState) stateMachine.getState("MoveLifterDown")).setReturnState(getName());
			gotoState("MoveLifterDown");
		} else {
			// Otherwise, raise the flipper
			gotoState("RaiseFlipper");
		}
	}

}
