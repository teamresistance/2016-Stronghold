package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.robostates.DelayState;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveLifter extends ReturnState {

	private boolean up = false;
	
	public MoveLifter(String returnState) {
		super(returnState);
	}
	
	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		if(IO.bottomLifterSwitch.get()) {
			up = true;
			IO.lifterMotor.set(Constants.LIFTER_UP_SPEED);
		} else if(IO.topLifterSwitch.get()) {
			up = false;
			IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);
		} else {
			up = false;
			IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);
		}
	}

	@Override
	public void update() {
		SmartDashboard.putBoolean("Up", up);
		SmartDashboard.putNumber("Lifter Motor Speed", IO.lifterMotor.get());
		if(IO.topLifterSwitch.get() && up) {
			stop();
		} else if(IO.bottomLifterSwitch.get() && !up) {
			stop();
		}
	}
	
	private void stop() {
		IO.lifterMotor.set(0.0);
		if(up) {
			((TopOutLifter)stateMachine.getState("TopOutLifter")).setReturnState(getReturnState());
			((DelayState)stateMachine.getState("DelayState")).setReturnState("TopOutLifter");
//			gotoState("TopOutLifter");
			gotoState("DelayState");
		} else {
			gotoReturnState();
		}
	}
	
	@Override
	public void onExit(StateTransition e) {

	}
}
