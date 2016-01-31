package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class DriveTrain extends State {

	private float angleOffset = 90;
	private float angleDeadband = 15;
	
	private boolean reverse = false;
	private boolean reverseLatch = false;
	
	protected DriveTrain(StateMachine stateMachine, String name) {
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
		if(IO.rightJoystick.getRawButton(1) && !reverseLatch) {
			reverseLatch = true;
			reverse = !reverse;
		} else if(!IO.rightJoystick.getRawButton(1) && reverseLatch) {
			reverseLatch = false;
		}
		
		if(IO.leftJoystick.getRawButton(4)) {
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
			return (float) -IO.rightJoystick.getY();
		} else {
			return (float) IO.leftJoystick.getY();
		}
	}
	
	protected float getRightY() {
		if(reverse) {
			return (float) -IO.leftJoystick.getY();
		} else {
			return (float) IO.rightJoystick.getY();
		}
	}

}
