package org.teamresistance.teleop;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.robostates.AntlerSnorflerUp;
import org.teamresistance.robostates.AntlersDown;
import org.teamresistance.robostates.SnorflerDown;
import org.teamresistance.teleop.driveModes.DirectDrive;
import org.teamresistance.teleop.driveModes.Idle;
import org.teamresistance.teleop.driveModes.ScaledDrive;
import org.teamresistance.teleop.driveModes.Target;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends State {
	
	private StateMachine driveModes;
	private String returnDriveMode; //Drive mode to goto after idle is exited
	
	private StateMachine antlerSnorflerMachine;
	
	private NetworkTable gripTable;

	public Teleop() {
		driveModes = new StateMachine();
		antlerSnorflerMachine = new StateMachine();

		gripTable = NetworkTable.getTable("GRIP/myContoursReport");
	}
	
	@Override
	public void init() {
		Target target = new Target();
		driveModes.addState(new ScaledDrive(target), "ScaledDrive");
		driveModes.addState(new DirectDrive(target), "DirectDrive");
		driveModes.addState(target, "Target");
		driveModes.addState(new Idle(), "Idle");
		
		antlerSnorflerMachine.addState(new AntlerSnorflerUp());
		antlerSnorflerMachine.addState(new AntlersDown());
		antlerSnorflerMachine.addState(new SnorflerDown());
	}

	@Override
	public void onEntry(StateTransition e) {
		driveModes.setState("ScaledDrive");
		antlerSnorflerMachine.setState("AntlerSnorflerUp");
	}

	@Override
	public void update() {
		JoystickIO.update();
		driveModes.update();
		antlerSnorflerMachine.update();
		
		SmartDashboard.putNumber("Roll", IO.imu.getRoll());
		SmartDashboard.putNumber("Pitch", IO.imu.getPitch());
		SmartDashboard.putNumber("Yaw", IO.imu.getYaw());
		
		SmartDashboard.putNumber("Frame Rate", gripTable.getNumber("frameRate", -1));
		
		double[] areas = gripTable.getNumberArray("area", new double[0]);
		for(int i = 0; i < areas.length; i++) {
			SmartDashboard.putNumber("Area " + i, areas[i]);
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}
	
	public void setDriveIdle() {
		returnDriveMode = driveModes.getCurrentState();
		driveModes.setState("Idle");
	}
	
	public void exitIdleDrive() {
		if(returnDriveMode != null) {
			driveModes.setState(returnDriveMode);
			returnDriveMode = null;
		}
	}
	
	/*
	 * Put down foot
	 * 
	 * Drive till hit portcullis
	 * lift foot
	 * Once rear limit switch is hit
	 */
	
	/*
	 * if buttonPressed move lifter to opposite position
	 * if buttonPressed move flipper to opposite position
	 * if buttonPressed && flipper is down begin portcullis routine
	 * 		if lifter not in lower position move to lower position
	 * 		raise flipper
	 * 		when bottom limit switch pressed start raising lifter
	 * 		when top limit switch pressed return controls to driver
	 */
	
}
