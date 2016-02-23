package org.teamresistance;

import org.teamresistance.util.io.InvertableDigitalInput;
import org.teamresistance.util.io.InvertableSolenoid;
import org.teamresistance.util.io.NavXIMU;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class IO {
	
	public static VictorSP leftDrive;
	public static VictorSP rightDrive;
	public static RobotDrive robotDrive;
	
	public static InvertableSolenoid snorflerSolenoid;
	public static VictorSP snorflerMotor;
	public static InvertableDigitalInput ballSensor;
	public static InvertableSolenoid shooterSolenoid;
	
	public static InvertableSolenoid antlerSolenoid;
	
	public static InvertableSolenoid lifterTiltSolenoid;
	public static VictorSP lifterMotor;
	public static InvertableDigitalInput bottomLifterSwitch;
	public static InvertableDigitalInput topLifterSwitch;
	
	public static InvertableSolenoid flipperSolenoid;
	
	public static InvertableDigitalInput bottomFlipperSwitch;
	public static InvertableDigitalInput middleLifterSwitch;
	public static InvertableDigitalInput topFlipperSwitch;
	
	public static Compressor compressor;
	public static Relay compressorRelay;
	
	public static NavXIMU imu;
	
	public static InvertableSolenoid lifterLight;
	public static InvertableSolenoid snorflerLight;
	
	public static void init() {
		leftDrive = new VictorSP(0);
		leftDrive.setInverted(true);
		rightDrive = new VictorSP(1);
		rightDrive.setInverted(true);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		
		snorflerSolenoid = new InvertableSolenoid(3);
		snorflerMotor = new VictorSP(3);
		ballSensor = new InvertableDigitalInput(0, true);
		
		shooterSolenoid = new InvertableSolenoid(5);
		
		antlerSolenoid = new InvertableSolenoid(1);
	
		lifterTiltSolenoid = new InvertableSolenoid(2);
		lifterMotor = new VictorSP(2);
		lifterMotor.setInverted(false);
		
		bottomLifterSwitch = new InvertableDigitalInput(5, true);
		middleLifterSwitch = new InvertableDigitalInput(4, true);
		topLifterSwitch = new InvertableDigitalInput(3);
		
		flipperSolenoid = new InvertableSolenoid(4);
		
		bottomFlipperSwitch = new InvertableDigitalInput(2, true);
		topFlipperSwitch = new InvertableDigitalInput(1, true);
		
		compressor = new Compressor();
		compressorRelay = new Relay(0);
		
		imu = new NavXIMU();
	
		lifterLight = new InvertableSolenoid(6);
		snorflerLight = new InvertableSolenoid(7);
	}
	
}
