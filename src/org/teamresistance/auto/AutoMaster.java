package org.teamresistance.auto;

import org.teamresistance.util.io.NavXGyro;


public class AutoMaster {
	
	public static NavXGyro imu;
	public static DriveToTower driver;
	
	public static void init() {
		imu = new NavXGyro();
		driver = new DriveToTower();
	}
}
