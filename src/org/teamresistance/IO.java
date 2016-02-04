package org.teamresistance;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;

import com.kauailabs.navx.frc.AHRS;

public class IO {
	
	public static Joystick leftJoystick;
	public static Joystick rightJoystick;
	public static Joystick codriverStick;
	
	public static VictorSP leftDrive;
	public static VictorSP rightDrive;
	public static RobotDrive robotDrive;
	
	public static AHRS imu;
	
	public static void init() {
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		codriverStick = new Joystick(2);
		
		leftDrive = new VictorSP(0);
		rightDrive = new VictorSP(1);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
		
		imu = new AHRS(SPI.Port.kMXP);
	}
	
}
