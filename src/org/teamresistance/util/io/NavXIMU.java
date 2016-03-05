package org.teamresistance.util.io;


import org.teamresistance.util.Util;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXIMU {
	private AHRS ahrs;	
	private boolean reversed;

	public NavXIMU() { //setup
		try {
			ahrs = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex ) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
	}
	
	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	
	public float wrap(float number) {
		if(Math.abs(number)<=180) {
			return number;
		} else {
			if(number > 180) {
				float temp = number %180;
				return -180 + temp;
			} else {
				float temp = number %180;
				return 180-temp;
			}
		}
	}
	
	public double getRoll() {
		return ahrs.getRoll();
	}
	
	public double getPitch() {
		return ahrs.getPitch();
	}
	
	public double getYaw() {
		SmartDashboard.putNumber("Raw Yaw", ahrs.getYaw());
		if(reversed) {
			int add;
			if(ahrs.getYaw() <= 0) {
				add = 180;
			}else {
				add = -180;
			}
			SmartDashboard.putNumber("Current Corrected Yaw" , (ahrs.getYaw()+add));
			return (ahrs.getYaw() + add);
		} else {
			return ahrs.getYaw(); //broken and will need fixin
		}
	}
	
	public boolean isStraight(int thresholdAngle, int targetAngle) {
		SmartDashboard.putNumber("targetAngle", targetAngle);
		return Math.abs(getYaw() - targetAngle) <= thresholdAngle;
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
			return targetAngle < getYaw();
	}

	public double turnTo(int angle, int threshold) {
		if(!isStraight(threshold, angle)) {
			/*if(reversed) {
				if(isLeft(angle)) {
					return -0.75;
				}
				else {
					//turn left 
					return 0.75;
				}
			} else {*/
				if(isLeft(angle)) {
					return 0.75;
				}
				else {
					//turn left 
					return -0.75;
				//}
			}
		}
		return 0.0;
	}
	
	
	public double calculateAngleCorrection(double desiredAngle) {
		double currentAngle = getYaw();
		double error = desiredAngle - currentAngle;

		// If our error is more than 2 degrees, calculate the rotation speed needed to correct it
		return Math.abs(error) > 2 ? Util.clip(error * 4.0, -1.0, 1.0) : 0;
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
	
	
	
	
	
	
	
	
	
	
