package org.teamresistance.teleop;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.robostates.AntlerSnorflerUp;
import org.teamresistance.robostates.AntlersDown;
import org.teamresistance.robostates.DelayState;
import org.teamresistance.robostates.SnorflerDown;
import org.teamresistance.robostates.lifter.DriveThroughDrawbridge;
import org.teamresistance.robostates.lifter.LeavePortcullis;
import org.teamresistance.robostates.lifter.LiftPortcullis;
import org.teamresistance.robostates.lifter.LowerDrawbridge;
import org.teamresistance.robostates.lifter.LowerFlipper;
import org.teamresistance.robostates.lifter.MoveLifter;
import org.teamresistance.robostates.lifter.MoveLifterDown;
import org.teamresistance.robostates.lifter.MoveLifterUp;
import org.teamresistance.robostates.lifter.RaiseFlipper;
import org.teamresistance.robostates.lifter.TeleopLifterIdle;
import org.teamresistance.robostates.lifter.TopOutLifter;
import org.teamresistance.teleop.driveModes.AngleHold;
import org.teamresistance.teleop.driveModes.DirectDrive;
import org.teamresistance.teleop.driveModes.Idle;
import org.teamresistance.teleop.driveModes.ScaledDrive;
import org.teamresistance.teleop.driveModes.AngleMatch;
import org.teamresistance.teleop.driveModes.Shoot;
import org.teamresistance.teleop.driveModes.Target;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Teleop extends State {
	
	private StateMachine driveModes;
	private String returnDriveMode; //Drive mode to goto after idle is exited
	
	private StateMachine antlerSnorflerMachine;
	private StateMachine lifterMachine;
	
	private NetworkTable gripTable;

	public Teleop() {
		driveModes = new StateMachine();
		antlerSnorflerMachine = new StateMachine();
		lifterMachine = new StateMachine();

		gripTable = NetworkTable.getTable("GRIP/myContoursReport");
	}
	
	@Override
	public void init() {
		AngleMatch target = new AngleMatch();
		driveModes.addState(new ScaledDrive(target), "ScaledDrive");
		driveModes.addState(new DirectDrive(target), "DirectDrive");
		driveModes.addState(target);
		driveModes.addState(new Idle(), "Idle");
		driveModes.addState(new Shoot());
		driveModes.addState(new Target());
		driveModes.addState(new AngleHold());
		
		antlerSnorflerMachine.addState(new AntlerSnorflerUp());
		antlerSnorflerMachine.addState(new AntlersDown());
		antlerSnorflerMachine.addState(new SnorflerDown());
		
		lifterMachine.addState(new LiftPortcullis(IO.lifterTiltSolenoid, IO.bottomLifterSwitch));
		lifterMachine.addState(new MoveLifter("TeleopLifterIdle"));
		lifterMachine.addState(new MoveLifterDown());
		lifterMachine.addState(new MoveLifterUp());
		lifterMachine.addState(new RaiseFlipper());
		lifterMachine.addState(new TeleopLifterIdle());
		DelayState delayState = new DelayState();
		delayState.setDelay(Constants.LIFTER_PAUSE_TIME);
		lifterMachine.addState(delayState);
		lifterMachine.addState(new TopOutLifter());
		lifterMachine.addState(new LeavePortcullis());
		lifterMachine.addState(new LowerFlipper());
		lifterMachine.addState(new LowerDrawbridge());
		lifterMachine.addState(new DriveThroughDrawbridge());
		
		
		SmartDashboard.putNumber("Speed", 0.0);
	}

	@Override
	public void onEntry(StateTransition e) {
		driveModes.setState("ScaledDrive");
		antlerSnorflerMachine.setState("AntlerSnorflerUp");
		lifterMachine.setState("TeleopLifterIdle");
	}

	@Override
	public void update() {
		SmartDashboard.putBoolean("TopFlipperSwitch", IO.topFlipperSwitch.get());
		SmartDashboard.putBoolean("BottomFlipperSwitch", IO.bottomFlipperSwitch.get());
		
		SmartDashboard.putBoolean("Compressor", IO.compressor.enabled());
		
		IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		
		JoystickIO.update();
		driveModes.update();
		antlerSnorflerMachine.update();
		lifterMachine.update();
//		IO.lifterMotor.set(JoystickIO.codriverStick.getY());
		
//		IO.robotDrive.drive(SmartDashboard.getNumber("Speed"), SmartDashboard.getNumber("Speed"));
		
		SmartDashboard.putNumber("Roll", IO.imu.getRoll());
		SmartDashboard.putNumber("Pitch", IO.imu.getPitch());
		SmartDashboard.putNumber("Yaw", IO.imu.getYaw());
		
//		IO.shooterSolenoid.set(JoystickIO.btnScore.isDown() && !IO.ballSensor.get());
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
