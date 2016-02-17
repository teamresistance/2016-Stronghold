package org.teamresistance;

import org.teamresistance.util.io.InvertableDigitalInput;
import org.teamresistance.util.io.InvertableSolenoid;
import org.teamresistance.util.io.InvertableVictorSP;
import org.teamresistance.util.io.NavXIMU;

import edu.wpi.first.wpilibj.RobotDrive;

public class IO {
	
	public static InvertableVictorSP leftDrive;
	public static InvertableVictorSP rightDrive;
	public static RobotDrive robotDrive;
	
	public static InvertableSolenoid snorflerSolenoid;
	public static InvertableVictorSP snorflerMotor;
	public static InvertableDigitalInput ballSensor;
	
	public static InvertableSolenoid antlerSolenoid;
	
	public static InvertableSolenoid gateGrabberSolenoid;
	public static InvertableSolenoid lifterTiltSolenoid;
	public static InvertableVictorSP lifterMotor;
	public static InvertableDigitalInput lifterLowerLimit;
	public static InvertableDigitalInput lifterUpperLimit;
	
	public static InvertableSolenoid flipperSolenoid;
	
	public static InvertableDigitalInput bottomFlipperSwitch;
	public static InvertableDigitalInput topFlipperSwitch;
	
	public static NavXIMU imu;
	
	public static void init() {
		leftDrive = new InvertableVictorSP(0);
		rightDrive = new InvertableVictorSP(1);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		
		snorflerSolenoid = new InvertableSolenoid(0);
		snorflerMotor = new InvertableVictorSP(3);
		ballSensor = new InvertableDigitalInput(0);
		
		antlerSolenoid = new InvertableSolenoid(1);
		
		gateGrabberSolenoid = new InvertableSolenoid(2);
		lifterTiltSolenoid = new InvertableSolenoid(4);
		lifterMotor = new InvertableVictorSP(4);
		
		lifterLowerLimit = new InvertableDigitalInput(3);
		lifterUpperLimit = new InvertableDigitalInput(4);
		
		flipperSolenoid = new InvertableSolenoid(3);
		
		bottomFlipperSwitch = new InvertableDigitalInput(1);
		topFlipperSwitch = new InvertableDigitalInput(2);
		
		imu = new NavXIMU();
	}
	
}
