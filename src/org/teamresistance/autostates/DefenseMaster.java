package org.teamresistance.autostates;

import org.teamresistance.util.io.NavXGyro;
import org.teamresistance.auto.AutoMaster;

public class DefenseMaster {
	public static NavXGyro imu;
	
	public static void init() {
		imu = AutoMaster.imu;
		
	}
}
