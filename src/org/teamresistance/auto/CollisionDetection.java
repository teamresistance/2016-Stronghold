package org.teamresistance.auto;

import org.teamresistance.IO;

class CollisionDetection {
	public static final double kCollisionThreshold_DeltaG = 0.5f;
	

	public void collisonDetection() {
		double last_world_linear_accel_x = 0;//set these some to some reasonably typical value in order to avoid never-initialized errors
		double last_world_linear_accel_y = 0;

		boolean collisionDetected = false;
    
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