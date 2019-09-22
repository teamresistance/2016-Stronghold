package org.teamresistance.util;

import org.teamresistance.util.io.NavXIMU;

public class SwingDetection {

	public static final double SWING_THRESHOLD = 60.0; // minimum pitch difference
	private final NavXIMU imu;

	// For now, use a reasonably typical value to avoid never-initialized errors
	private double lastPitch = 0.5;

	public SwingDetection(NavXIMU imu) {
		this.imu = imu;
	}

	public boolean isSwinging() {
		double currentPitch = imu.getPitch();
		double deltaPitch = lastPitch - currentPitch;
		lastPitch = currentPitch;

		return Math.abs(deltaPitch) > SWING_THRESHOLD;
	}

}