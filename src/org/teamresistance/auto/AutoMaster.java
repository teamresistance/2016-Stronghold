package org.teamresistance.auto;

import org.teamresistance.util.io.NavXGyro;


public class AutoMaster {
	
	public NavXGyro imu;
	public DriveToTower driver;
	
	public void init() {
		imu = new NavXGyro();
		driver = new DriveToTower();
		
	}
}
