package org.teamresistance.util.io;

import com.kauailabs.navx.frc.AHRS;

import org.teamresistance.IO;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

public class NavXIMU {
	AHRS ahrs;	

	public NavXIMU() { //setup
		try {
			ahrs = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}
	
	public double getRoll() {
		return ahrs.getRoll();
	}
	
	public double getPitch() {
		return ahrs.getPitch();
	}
	
	public double getYaw() {
		return ahrs.getYaw();
	}
	
	public boolean isStraight(int thresholdAngle, int startAngle) {
		return Math.abs(ahrs.getYaw() - startAngle) <= thresholdAngle;
	}
	
	public boolean isPitchLevel(int thresholdAngle, int startAngle) {
		return Math.abs(ahrs.getPitch() - startAngle) <= thresholdAngle;
	}

	public boolean isRollLevel(int thresholdAngle, int startAngle) {
		return Math.abs(ahrs.getRoll() - startAngle) <= thresholdAngle;
	}
	
	public boolean isLevel(int thresholdAngle, int startRoll, int startPitch) {
		return isPitchLevel(thresholdAngle, startPitch)
				&& isRollLevel(thresholdAngle, startRoll);
	}
	
	public boolean isLeft(int targetAngle) {
		return targetAngle < ahrs.getYaw();
	}

	public double turnTo(int angle, int threshold) {
		while(!isStraight(threshold, angle)) {
			if(isLeft(angle)) {
				return 0.5;
			}
			else {
				//turn left 
				return -0.5;
			}
		}
		return 0.0;
	}
	
}
	

	/*public void rotateToAngle(AHRS ahrs) {
		PIDController turnController;
		
		final double kP = 0.03;
	    final double kI = 0.00;
	    final double kD = 0.00;
	    final double kF = 0.00;
	    
	    final double kToleranceDegrees = 2.0f;
	    
	    turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        
        
		
	}*/
	
	
	
	
	
	
	
	
	
	
