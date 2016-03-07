package org.teamresistance.util.io;


import com.kauailabs.navx.frc.AHRS;

import org.teamresistance.util.Util;
import org.teamresistance.util.annotation.Experimental;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXIMU {

	public static final int ANGLE_ERROR_THRESHOLD = 2; // degrees

	private final AHRS ahrs;
	private boolean reversed;

	public NavXIMU() {
		try {
			ahrs = new AHRS(SPI.Port.kMXP); 
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
			throw ex; // Rethrow. We gonn' die anyway.
		}
	}
	
	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	
	public double getRoll() {
		return ahrs.getRoll();
	}
	
	public double getPitch() {
		return ahrs.getPitch();
	}
	
	public double getYaw() {
		double rawYaw = ahrs.getYaw();
		SmartDashboard.putNumber("Raw Yaw", rawYaw);

		if (reversed) {
			double correction = rawYaw <= 0 ? 180 : -180;
			double correctedYaw = rawYaw + correction;
			SmartDashboard.putNumber("Corrected Yaw" , correctedYaw);
			return correctedYaw;
		} else {
			return rawYaw;
		}
	}
	
	public boolean isStraight(int thresholdAngle, int targetAngle) {
		double error = Math.abs(getYaw() - targetAngle);
		return  error <= thresholdAngle;
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

	@Experimental
	public double turnTo(int setPoint, int threshold) {
		// TODO new algorithm, possible move it outside of this class
		double counterClockWise = 0.65; // const. magnitude of rotation speed
		double clockWise = -1 * counterClockWise;

		double feedback = getYaw();
		double error = setPoint - feedback;

		if (Math.abs(error) <= threshold) {
			return 0;
		}

		if (error < 0) {
			if (Math.abs(error) > 180) {
				return clockWise;
			} else {
				return counterClockWise;
			}
		} else {
			if (Math.abs(error) > 180) {
				return clockWise;
			} else{
				return counterClockWise;
			}
		}
	}

	@Experimental
	public double calculateAngleCorrection(double desiredAngle) {
		double currentAngle = getYaw();
		double error = desiredAngle - currentAngle;

		// If our error is too great, calculate the rotation speed needed to correct it
		return Math.abs(error) > ANGLE_ERROR_THRESHOLD ? Util.clip(error * 4.0, -1.0, 1.0) : 0;
	}
}
