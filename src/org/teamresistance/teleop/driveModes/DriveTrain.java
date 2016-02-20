package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveTrain extends State {

	private AngleMatch target;
	
	private float angleOffset = 90;
	private float angleDeadband = 15;
	
	private boolean reverse = false;
	
	public DriveTrain(AngleMatch target) {
		this.target = target;
	}
	
	@Override
	public void init() {
		IO.lifterLight.set(reverse);
		IO.snorflerLight.set(!reverse);
	}

	@Override
	public void onEntry(StateTransition e) {
		IO.lifterLight.set(reverse);
		IO.snorflerLight.set(!reverse);
	}

	@Override
	public void update() {
		if(JoystickIO.btnDriveReverse.onButtonPressed()) {
			reverse = !reverse;
			IO.lifterLight.set(reverse);
			IO.snorflerLight.set(!reverse);
		}
		if(JoystickIO.btnAngleHold.isDown()) {
			((AngleHold)stateMachine.getState("AngleHold")).setReturnState(getName());
			gotoState("AngleHold");
		} else if(JoystickIO.btnScore.onButtonPressed()) {
			((Shoot)stateMachine.getState("Shoot")).setReturnState(getName());
			if(Math.abs((30+angleOffset) - IO.imu.getYaw()) < angleDeadband) {
				target.setTargetAngle(30+angleOffset);
				gotoState("LoadToddsBall");
			} else if(Math.abs((-30-angleOffset) - IO.imu.getYaw()) < angleDeadband) {
				target.setTargetAngle(-30-angleOffset);
				gotoState("LoadToddsBall");
			} else if(Math.abs((0+angleOffset) - IO.imu.getYaw()) < angleDeadband) {
				target.setTargetAngle(0+angleOffset);
				gotoState("LoadToddsBall");
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
