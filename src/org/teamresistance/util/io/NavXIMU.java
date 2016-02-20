package org.teamresistance.util.io;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

import java.lang.Math;

import org.teamresistance.IO;

import com.kauailabs.navx.frc.AHRS;

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
		boolean isStraight = false;
		
		double angle = ahrs.getYaw();
		
		if(Math.abs(angle-startAngle)<=thresholdAngle) isStraight = true;
		
		return isStraight;
	}
	
	public boolean isPitchLevel(int thresholdAngle, int startAngle) {
		boolean pitchLevel = false;
		
		double angle = ahrs.getPitch();
		
		if(Math.abs(angle-startAngle)<=thresholdAngle) pitchLevel = true;
		
		return pitchLevel;
		
	}

	public boolean isRollLevel(int thresholdAngle, int startAngle) {
		boolean rollLevel = false;
		
		double angle = ahrs.getRoll();
		
		if(Math.abs(angle-startAngle)<=thresholdAngle) rollLevel = true;
		
		return rollLevel;
		
	}
	
	public boolean isLevel(int thresholdAngle, int startRoll, int startPitch) {
		boolean isLevel = false;
		boolean pitchLevel = isPitchLevel(thresholdAngle, startPitch);
		boolean rollLevel = isRollLevel(thresholdAngle, startRoll);
		
		if(pitchLevel&&rollLevel) isLevel = true;
		
		return isLevel;
	}
	
	public boolean isLeft(int targetAngle) {
		boolean isLeft = false;
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
	
	
	
	
	
	
	
	
	
	
