package org.teamresistance;

import org.teamresistance.util.io.InvertableSolenoid;
import org.teamresistance.util.io.InvertableVictorSP;
import org.teamresistance.util.io.NavXGyro;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;

public class IO {
	
	public static InvertableVictorSP leftDrive;
	public static InvertableVictorSP rightDrive;
	public static RobotDrive robotDrive;
	
	public static InvertableSolenoid snorflerSolenoid;
	public static InvertableVictorSP snorflerMotor;
	public static DigitalInput ballSensor;
	
	public static InvertableSolenoid antlerSolenoid;
	
	public static InvertableSolenoid gateGrabberSolenoid;
	public static InvertableVictorSP gateLifterMotor;
	
	public static InvertableSolenoid footSolenoid;
	public static DigitalInput footSwitch;
	
	public static DigitalInput screwSwitch;
	
	public static InvertableSolenoid shooterSolenoid;
	
	public static AHRS imu;
	public static NavXGyro navX;
	
	public static void init() {
		leftDrive = new InvertableVictorSP(0);
		rightDrive = new InvertableVictorSP(1);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		
		snorflerSolenoid = new InvertableSolenoid(0);
		snorflerMotor = new InvertableVictorSP(3);
		ballSensor = new DigitalInput(0);
		
		antlerSolenoid = new InvertableSolenoid(1);
		
		gateGrabberSolenoid = new InvertableSolenoid(2);
		gateLifterMotor = new InvertableVictorSP(4);
		
		footSolenoid = new InvertableSolenoid(3);
		shooterSolenoid = new InvertableSolenoid(5);
		
		footSwitch = new DigitalInput(1);
		
		navX = new NavXGyro();
		
		try {
			imu = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}
	
}
