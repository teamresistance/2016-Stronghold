package org.teamresistance;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

import com.kauailabs.navx.frc.AHRS;

public class IO {
	
	public static VictorSP leftDrive;
	public static VictorSP rightDrive;
	public static RobotDrive robotDrive;
	
	public static Solenoid snorflerSolenoid;
	public static VictorSP snorflerMotor;
	public static DigitalInput ballSensor;
	
	public static Solenoid antlerSolenoid;
	
	public static Solenoid gateGrabberSolenoid;
	public static VictorSP gateLifterMotor;
	
	public static Solenoid flipperSolenoid;
	
	public static AHRS imu;
	
	public static void init() {
		leftDrive = new VictorSP(0);
		rightDrive = new VictorSP(1);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		
		snorflerSolenoid = new Solenoid(0);
		snorflerMotor = new VictorSP(3);
		ballSensor = new DigitalInput(0);
		
		antlerSolenoid = new Solenoid(1);
		
		gateGrabberSolenoid = new Solenoid(2);
		gateLifterMotor = new VictorSP(4);
		
		flipperSolenoid = new Solenoid(3);
		
		imu = new AHRS(SPI.Port.kMXP);
	}
	
}
