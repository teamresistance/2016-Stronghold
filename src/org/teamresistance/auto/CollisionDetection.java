package org.teamresistance.auto;

import org.teamresistance.IO;

public class CollisionDetection {
	public static final double kCollisionThreshold_DeltaG = 0.5f;
	private boolean collisionDetected;
	
	public boolean detected() { return collisionDetected; }

	public void collisonDetection() {
		double last_world_linear_accel_x = 0.5;//set these some to some reasonably typical value in order to avoid never-initialized errors
		double last_world_linear_accel_y = 0.5;

		collisionDetected = false;
    
		double curr_world_linear_accel_x = IO.imu.getWorldLinearAccelX();
		double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
		last_world_linear_accel_x = curr_world_linear_accel_x;
		
		double curr_world_linear_accel_y = IO.imu.getWorldLinearAccelY();
		double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
		last_world_linear_accel_y = curr_world_linear_accel_y;
    
		if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
			( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
			collisionDetected = true;
		}
	}
}