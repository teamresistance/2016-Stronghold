package org.teamresistance.auto;

import org.teamresistance.IO;

public class SwingDetection {
	public static final double swingThreshold = 60;
	private boolean swingDetected;

	// TODO replace with an isSwinging, inline with swingDetection
	public boolean detected() { return swingDetected; }

	public void swingDetection() {
		double lastPitch = 0.5;//set these some to some reasonably typical value in order to avoid never-initialized errors

		swingDetected = false;
    
		double currentPitch = IO.imu.getPitch();
		double deltaPitch = lastPitch - currentPitch;
		lastPitch = currentPitch;
    
		if ( ( Math.abs(deltaPitch) > swingThreshold )) {
			swingDetected = true;
		}
	}
}