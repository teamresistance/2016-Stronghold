package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class DriveTrain extends State {

	private float angleOffset = 90;
	private float angleDeadband = 15;
	
	private boolean reverse = false;

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {

	}

	@Override
	public void update() {
		if(JoystickIO.btnDriveReverse.onButtonPressed()) {
			reverse = !reverse;
		}
		
		if(JoystickIO.btnScore.onButtonPressed()) {
			if(Math.abs((30+angleOffset) - IO.imu.getYaw()) < angleDeadband) {
				Target.setTargetAngle(30+angleOffset);
				gotoState("Target");
			} else if(Math.abs((-30-angleOffset) - IO.imu.getYaw()) < angleDeadband) {
				Target.setTargetAngle(-30-angleOffset);
				gotoState("Target");
			} else if(Math.abs((0+angleOffset) - IO.imu.getYaw()) < angleDeadband) {
				Target.setTargetAngle(0+angleOffset);
				gotoState("Target");
			}
		}
	}

	@Override
	public void onExit(StateTransition e) {

	}
	
	protected float getLeftY() {
		if(reverse) {
			return (float) -JoystickIO.rightJoystick.getY();
		} else {
			return (float) JoystickIO.leftJoystick.getY();
		}
	}
	
	protected float getRightY() {
		if(reverse) {
			return (float) -JoystickIO.leftJoystick.getY();
		} else {
			return (float) JoystickIO.rightJoystick.getY();
		}
	}

}
