package org.teamresistance.teleop;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.robostates.AntlerSnorflerUp;
import org.teamresistance.robostates.AntlersDown;
import org.teamresistance.robostates.SnorflerDown;
import org.teamresistance.teleop.driveModes.AngleHold;
import org.teamresistance.teleop.driveModes.AngleMatch;
import org.teamresistance.teleop.driveModes.DirectDrive;
import org.teamresistance.teleop.driveModes.Idle;
import org.teamresistance.teleop.driveModes.LoadToddsBall;
import org.teamresistance.teleop.driveModes.ScaledDrive;
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
	
	private NetworkTable gripTable; // unused

	public Teleop(StateMachine lifterMachine) {
		driveModes = new StateMachine();
		antlerSnorflerMachine = new StateMachine();
		this.lifterMachine = lifterMachine;

		gripTable = NetworkTable.getTable("GRIP/myContoursReport");

		AngleMatch target = new AngleMatch();
		driveModes.addState(new ScaledDrive(target), "ScaledDrive");
		driveModes.addState(new DirectDrive(target), "DirectDrive");
		driveModes.addState(target);
		driveModes.addState(new Idle(), "Idle");
		driveModes.addState(new Shoot());
		driveModes.addState(new Target());
		driveModes.addState(new AngleHold());
		driveModes.addState(new LoadToddsBall());
		
		antlerSnorflerMachine.addState(new AntlerSnorflerUp());
		antlerSnorflerMachine.addState(new AntlersDown());
		antlerSnorflerMachine.addState(new SnorflerDown());
		
//		lifterMachine.addState(new LiftPortcullis(IO.lifterTiltSolenoid, IO.bottomLifterSwitch));
//		lifterMachine.addState(new MoveLifter("TeleopLifterIdle"));
//		lifterMachine.addState(new MoveLifterDown());
//		lifterMachine.addState(new MoveLifterUp());
//		lifterMachine.addState(new RaiseFlipper());
//		lifterMachine.addState(new TeleopLifterIdle());
//		DelayState delayState = new DelayState();
//		delayState.setDelay(Constants.LIFTER_PAUSE_TIME);
//		lifterMachine.addState(delayState);
//		lifterMachine.addState(new TopOutLifter());
//		lifterMachine.addState(new LeavePortcullis());
//		lifterMachine.addState(new LowerFlipper());
//		lifterMachine.addState(new LowerDrawbridge());
//		lifterMachine.addState(new DriveThroughDrawbridge());
		
		
		SmartDashboard.putNumber("Speed", 0.0);
	}

	@Override
	public void onEntry(StateTransition e) {
		driveModes.setState("ScaledDrive");
		antlerSnorflerMachine.setState("AntlerSnorflerUp");
		lifterMachine.setState("TeleopLifterIdle");
		IO.lifterLight.set(false);
		IO.snorflerLight.set(true);
	}

	@Override
	public void update() {
		SmartDashboard.putBoolean("TopFlipperSwitch", IO.topFlipperSwitch.get());
		SmartDashboard.putBoolean("BottomFlipperSwitch", IO.bottomFlipperSwitch.get());
		
		SmartDashboard.putBoolean("Compressor", IO.compressor.enabled());
		
		SmartDashboard.putBoolean("Bottom Lifter Switch", IO.bottomLifterSwitch.get());
		SmartDashboard.putBoolean("Middle Lifter Switch", IO.middleLifterSwitch.get());
		SmartDashboard.putBoolean("Top Lifter Switch", IO.topLifterSwitch.get());
		
		IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);

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
