package org.teamresistance.teleop.driveModes;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

import java.lang.Math;

import org.teamresistance.IO;

public class NavXGyro {
	AHRS ahrs;	

	public NavXGyro() { //setup
	
		try {
			ahrs = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}
	
	public double getRollAngle() {
		return ahrs.getRoll();
	}
	
	public double getPitchAngle() {
		return ahrs.getPitch();
	}
	
	public double getYawAngle() {
		return ahrs.getYaw();
	}
	
	public Boolean isStraight(int thresholdAngle, int startAngle) {
		Boolean isStraight = false;
		
		double angle = ahrs.getYaw();
		
		if(Math.abs(angle-startAngle)<=thresholdAngle) isStraight = true;
		
		return isStraight;
	}
	
	public Boolean pitchLevel(int thresholdAngle, int startAngle) {
		Boolean pitchLevel = false;
		
		double angle = ahrs.getPitch();
		
		if(Math.abs(angle-startAngle)<=thresholdAngle) pitchLevel = true;
		
		return pitchLevel;
		
	}

	public Boolean rollLevel(int thresholdAngle, int startAngle) {
		Boolean rollLevel = false;
		
		double angle = ahrs.getRoll();
		
		if(Math.abs(angle-startAngle)<=thresholdAngle) rollLevel = true;
		
		return rollLevel;
		
	}
	
	public Boolean isLevel(int thresholdAngle, int startRoll, int startPitch) {
		Boolean isLevel = false;
		Boolean pitchLevel = pitchLevel(thresholdAngle, startPitch);
		Boolean rollLevel = rollLevel(thresholdAngle, startRoll);
		
		if(pitchLevel&&rollLevel) isLevel = true;
		
		return isLevel;
	}
	
	public Boolean isLeft(int targetAngle) {
		Boolean isLeft = false;
		double angle = ahrs.getYaw();
		
		if(targetAngle<angle) isLeft = true; //need to check this to make sure it's going the right way
		
		return isLeft;
	}
	
	public void turnTo(int angle, int threshold) {
		while(!isStraight(threshold, angle)) {
			if(isLeft(angle)) {
				IO.robotDrive.arcadeDrive(0, 0.5);
			}
			else {
				//turn left 
				IO.robotDrive.arcadeDrive(0, -0.5);
			}
		}
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
	
	
	
	
	
	
	
	
	
	
