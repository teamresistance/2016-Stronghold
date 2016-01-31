package org.teamresistance.teleop;

import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.DirectDrive;
import org.teamresistance.teleop.driveModes.ScaledDrive;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends State {
	
	private StateMachine driveModes;
	
	protected Teleop(StateMachine stateMachine) {
		super(stateMachine);
	}

	@Override
	public void init() {
		driveModes = new StateMachine();
		driveModes.addState(ScaledDrive.class, "ScaledDrive");
		driveModes.addState(DirectDrive.class, "DirectDrive");
	}

	@Override
	public void onEntry(StateTransition e) {
		driveModes.setState("ScaledDrive"); 
	}

	@Override
	public void update() {
		driveModes.update();
		if(Math.abs(IO.codriverStick.getY()) > .1) {
			IO.armMotor.set(IO.codriverStick.getY());
		}else {
			IO.armMotor.set(0);
		}
		SmartDashboard.putNumber("Roll", IO.imu.getRoll());
		SmartDashboard.putNumber("Pitch", IO.imu.getPitch());
		SmartDashboard.putNumber("Yaw", IO.imu.getYaw());
	}

	@Override
	public void onExit(StateTransition e) {
		
	}
	
}
