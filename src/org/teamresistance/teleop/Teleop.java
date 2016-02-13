package org.teamresistance.teleop;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.robostates.AntlerSnorflerUp;
import org.teamresistance.robostates.AntlersDown;
import org.teamresistance.robostates.SnorflerDown;
import org.teamresistance.teleop.driveModes.DirectDrive;
import org.teamresistance.teleop.driveModes.ScaledDrive;
import org.teamresistance.teleop.driveModes.Target;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends State {
	
	private StateMachine driveModes;
	private StateMachine antlerSnorflerMachine;
	
	private NetworkTable gripTable;
	
	protected Teleop(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		driveModes = new StateMachine();
		driveModes.addState(ScaledDrive.class, "ScaledDrive");
		driveModes.addState(DirectDrive.class, "DirectDrive");
		driveModes.addState(Target.class, "Target");
		
		antlerSnorflerMachine = new StateMachine();
		antlerSnorflerMachine.addState(AntlerSnorflerUp.class);
		antlerSnorflerMachine.addState(AntlersDown.class);
		antlerSnorflerMachine.addState(SnorflerDown.class);
		
		gripTable = NetworkTable.getTable("GRIP/myContoursReport");
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
	
}
