package org.teamresistance;

import org.teamresistance.util.io.InvertableSolenoid;
import org.teamresistance.util.io.InvertableVictorSP;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;

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
	
	public static InvertableSolenoid flipperSolenoid;
	
	public static AHRS imu;
	
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
		
		flipperSolenoid = new InvertableSolenoid(3);
		
		imu = new AHRS(SPI.Port.kMXP);
	}
	
}
