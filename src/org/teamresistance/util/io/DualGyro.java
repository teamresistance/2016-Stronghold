package org.teamresistance.util.io;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DualGyro {
	
	private AnalogGyro gyro1;
	private boolean gyro1Inverted = false;
	private AnalogGyro gyro2;
	private boolean gyro2Inverted = false;
	
	public DualGyro() {
		gyro1 = new AnalogGyro(0);
		gyro2 = new AnalogGyro(1);
	}
	
	public void init() {
		reset();
		gyro1.initGyro();
		gyro2.initGyro();
	}
	
	public void reset() {
		gyro1.reset();
		gyro2.reset();
	}
	
	public double getAngle() {
		double averageAngle = ((gyro1Inverted ? -1 : 1) * gyro1.getAngle() + (gyro2Inverted ? -1 : 1) * gyro2.getAngle()) / 2.0f - 90.0;
//		if(averageAngle < 0) {
//			return 360 - Math.abs(averageAngle) % 360;
//		} else if(averageAngle > 360) {
//			return Math.abs(averageAngle) % 360;
//		} else {
			return averageAngle;
//		}
	}
	
	public void setInvertedGyro(int gyro, boolean inverted) {
		switch(gyro) {
		case 0:
			gyro1Inverted = inverted;
			break;
		case 1:
			gyro2Inverted = inverted;
			break;
		}
	}
	
	public void smartdashboardPut() {
		SmartDashboard.putNumber("Average Angle", getAngle());
	}
	
}
