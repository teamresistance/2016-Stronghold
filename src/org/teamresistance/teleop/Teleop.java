package org.teamresistance.teleop;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.Position;
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
	
	private NetworkTable gripTable;
	
	private Position antler = Position.UP;
	private Position snorfler = Position.UP;
	
	private double snorflerPickUPSpeed = 0.75;
	private double snorflerLoadSpeed = -0.35;
	
	protected Teleop(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		driveModes = new StateMachine();
		driveModes.addState(ScaledDrive.class, "ScaledDrive");
		driveModes.addState(DirectDrive.class, "DirectDrive");
		driveModes.addState(Target.class, "Target");
		
		gripTable = NetworkTable.getTable("GRIP");
	}

	@Override
	public void onEntry(StateTransition e) {
		driveModes.setState("ScaledDrive"); 
	}

	@Override
	public void update() {
		JoystickIO.update();
		driveModes.update();
		
		if(JoystickIO.btnAntler.onButtonPressed()) {
			if(antler == snorfler && antler == Position.UP) {
				antler = Position.next(antler);
				IO.antlerSolenoid.set(!IO.antlerSolenoid.get());
			} else if(antler != snorfler && antler == Position.DOWN) {
				antler = Position.next(antler);
				IO.antlerSolenoid.set(!IO.antlerSolenoid.get());
			}
		}
		
		if(JoystickIO.btnSnorfler.onButtonPressed()) {
			if(antler == snorfler && snorfler == Position.UP) {
				snorfler = Position.next(snorfler);
				IO.snorflerSolenoid.set(!IO.snorflerSolenoid.get());
				IO.snorflerMotor.set(snorflerPickUPSpeed);
			} else if(antler != snorfler && snorfler == Position.DOWN) {
				snorfler = Position.next(snorfler);
				IO.snorflerSolenoid.set(!IO.snorflerSolenoid.get());
				IO.snorflerMotor.set(0);
			}
		}
		
		if(snorfler == Position.DOWN) {
			if(IO.ballSensor.get() && IO.snorflerMotor.get() != 0) {
				IO.snorflerMotor.set(0);
				IO.snorflerSolenoid.set(false);
			}
		} else {
			if(!IO.ballSensor.get() && IO.snorflerMotor.get() != 0) {
				IO.snorflerMotor.set(0);
			}
		}
		
		if(JoystickIO.btnCancelSnorfle.onButtonPressed()) {
			if(IO.snorflerMotor.get() != 0) {
				IO.snorflerMotor.set(0);
			} else {
				if(snorfler == Position.DOWN && !IO.ballSensor.get()) {
					IO.snorflerMotor.set(snorflerPickUPSpeed);
				} else if(snorfler == Position.UP && IO.ballSensor.get()) {
					IO.snorflerMotor.set(snorflerLoadSpeed);
				}
			}
		}
		
		SmartDashboard.putNumber("Roll", IO.imu.getRoll());
		SmartDashboard.putNumber("Pitch", IO.imu.getPitch());
		SmartDashboard.putNumber("Yaw", IO.imu.getYaw());
		
		SmartDashboard.putNumber("Frame Rate", gripTable.getNumber("frameRate", -1));
	}

	@Override
	public void onExit(StateTransition e) {
		
	}
	
}
