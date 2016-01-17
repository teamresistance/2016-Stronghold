package org.teamresistance;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class IO {
	
	public static VictorSP leftDrive;
	public static VictorSP rightDrive;
	public static RobotDrive robotDrive;
	
	public static void init() {
		leftDrive = new VictorSP(0);
		rightDrive = new VictorSP(1);
		robotDrive = new RobotDrive(leftDrive, rightDrive);
	}
	
}
