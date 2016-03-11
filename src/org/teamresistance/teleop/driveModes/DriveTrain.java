package org.teamresistance.teleop.driveModes;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends State {

	private AngleMatch target;
	
	private float angleOffset = -90;
	private float angleDeadband = 5;//15

	private boolean reverse = false;

	private boolean initialized;

	public DriveTrain(AngleMatch target) {
		this.target = target;
	}

	@Override
	public void onEntry(StateTransition e) {
		// Temporary, since we chucked the init method() and putting
		// this in the constructor may be not be a good idea.
		if (!initialized) {
			IO.lifterLight.set(reverse);
			IO.snorflerLight.set(!reverse);
			initialized = true;
		}

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
		}
		/*else if(JoystickIO.btnScore.onButtonPressed()) {
			((Shoot)stateMachine.getState("Shoot")).setReturnState(getName());
			if(Math.abs(-30 - IO.imu.getYaw()) < angleDeadband) {
				SmartDashboard.putNumber("!!!!!&&&%%Nearest", -30);
				target.setTargetAngle(-30);
				gotoState("LoadToddsBall");
			} else if(Math.abs(-90 - IO.imu.getYaw()) < angleDeadband) {
				target.setTargetAngle(-90);
				SmartDashboard.putNumber("!!!!!&&&%%Nearest", -90);
				gotoState("LoadToddsBall");
			} else if(Math.abs(-150 - IO.imu.getYaw()) < angleDeadband) {
				target.setTargetAngle(-150);
				SmartDashboard.putNumber("!!!!!&&&%%Nearest", -150);
				gotoState("LoadToddsBall");
			} else {
				SmartDashboard.putNumber("!!!!!&&&%%Nearest", Double.POSITIVE_INFINITY);
			}
		}*/


		if(Math.abs(-30 - IO.imu.getYaw()) < angleDeadband) {
			shoot();
		} else if(Math.abs(-90 - IO.imu.getYaw()) < angleDeadband) {
			shoot();
		} else if(Math.abs(-150 - IO.imu.getYaw()) < angleDeadband) {
			shoot();
		} else if(JoystickIO.btnScore.isDown() && JoystickIO.btnCancel.onButtonPressed()) {
			((Shoot)stateMachine.getState("Shoot")).setReturnState(getName());
			gotoState("LoadToddsBall");
		} else  {
			SmartDashboard.putBoolean("SHOOTABLE", false);
		}
	}

	private void shoot() {
		SmartDashboard.putBoolean("SHOOTABLE", true);
		if(JoystickIO.btnScore.onButtonPressed()) {
			((Shoot)stateMachine.getState("Shoot")).setReturnState(getName());
			gotoState("LoadToddsBall");
		}
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
