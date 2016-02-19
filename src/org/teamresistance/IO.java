package org.teamresistance;

import org.teamresistance.util.io.InvertableSolenoid;
import org.teamresistance.util.io.InvertableVictorSP;
import org.teamresistance.util.io.NavXIMU;
import org.teamresistance.util.io.InvertableDigitalInput;
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
		
	public static AHRS imu;
	public static NavXIMU navX;
	
	public static DigitalInput screwSwitch;
	
	public static InvertableSolenoid shooterSolenoid;
	
	
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
		
		shooterSolenoid = new InvertableSolenoid(5);
		
		navX = new NavXIMU();
		
		try {
			imu = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}
	
}
