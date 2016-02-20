package org.teamresistance.robostates.lifter;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LowerDrawbridge extends State {
	
	private double startTime;
	private boolean delay = false;
	private double delayStart;
	
	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		SmartDashboard.putBoolean("LowerDrawbridge", true);
		IO.lifterTiltSolenoid.set(true);
		IO.flipperSolenoid.set(false);
		if(!IO.topLifterSwitch.get()) {
			SmartDashboard.putBoolean("Lower Lifter", true);
			MoveLifter moveLifter = ((MoveLifter)stateMachine.getState("MoveLifter"));
			moveLifter.setReturnState(getName());
			moveLifter.moveUp();
			gotoState("MoveLifter");
			return;
		}
		IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_MOVE_SPEED, 0);
		IO.lifterMotor.set(Constants.DRAWBRIDGE_LOWER_SPEED);
	}

	@Override
	public void update() {
		
		if(IO.middleLifterSwitch.get() && !delay) {
			IO.lifterMotor.set(0);
			IO.robotDrive.arcadeDrive(0, 0);
			SmartDashboard.putBoolean("LowerFlipper", false);
			gotoState("LowerFlipper");
			delay = true;
			delayStart = Time.getTime();
		} else {
			if(Time.getTime() - startTime < Constants.DRAWBRIDGE_FORWARD_TIME) {
				IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_FORWARD_SPEED, 0);
			} else if(Time.getTime() - startTime < Constants.DRAWBRIGDE_BACKUP_TIME){
				IO.robotDrive.arcadeDrive(Constants.DRAWBRIDGE_MOVE_SPEED, 0);	
			} else {
				IO.robotDrive.arcadeDrive(0, 0);
			}
		}
		if(IO.bottomLifterSwitch.get()) {
			IO.lifterMotor.set(0);
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(delay && Time.getTime() - delayStart <= Constants.DRAWBRIDGE_LOWER_DELAY) {
			IO.lifterMotor.set(Constants.LIFTER_DOWN_SPEED);
		} else if(delay) {
			IO.lifterMotor.set(0);
		}
	}

	@Override
	public void onExit(StateTransition e) {
		IO.robotDrive.arcadeDrive(0, 0);
	}
}
