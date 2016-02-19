package org.teamresistance.auto.defense;

import org.teamresistance.Constants;
import org.teamresistance.auto.CollisionDetection;
import org.teamresistance.IO;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefensePortcullis extends Defense {
	static double elapsed = 0.0;
	private boolean lifted = false;
	CollisionDetection detector;

	@Override
	public void beginCrossing() { //set the flipper to down, and lower the lifter all the way down
		detector = new CollisionDetection();
		lowerFlipper();
		lifterToBottom();
	}

	@Override
	public void whileCrossing() { //need to drive forward, raise flipper, lift motor to top, drive forward until level
		elapsed += Time.getDelta();
		if (!detector.detected() && elapsed<0.1) { //drive forward to the portcullis - will ideally implement collision detection here
			IO.robotDrive.arcadeDrive(0.5, 0);
		} 
		else {
			SmartDashboard.putString("Collision detector: ", "detected");
			if(!lifted) {
				raiseFlipper();
				lifterToTop();
				lifted = true;
			}
		}
		
		if(!IO.navX.isLevel(10, 0, 0) && elapsed <= 2.0) {
			// drive forward
			IO.robotDrive.arcadeDrive(0.5, 0);
		}
	}
	
	private void lifterToTop() {
		while(!IO.lifterUpperLimit.get()) {
			IO.lifterMotor.set(Constants.SCREW_THREAD_DOWN_SPEED);
		}
		IO.lifterMotor.set(0.0);
	}

	private void lifterToBottom() {
		while (!IO.lifterLowerLimit.get()) { //drive the lifter down all the way
			IO.lifterMotor.set(Constants.SCREW_THREAD_UP_SPEED);
		}
		IO.lifterMotor.set(0.0);
	}
	
	private void lowerFlipper() {
		while (!IO.bottomFlipperSwitch.get()) {
			IO.flipperSolenoid.set(false);
		}
	}
	private void raiseFlipper() {
		while(!IO.topFlipperSwitch.get()) {
			IO.flipperSolenoid.set(true); // raise foot
		}
	}

	
	public void collisonDetection() {
		double kCollisionThreshold_DeltaG = 0.5f;
		boolean collisionDetected;
	
		double last_world_linear_accel_x = 0;//set these some to some reasonably typical value in order to avoid never-initialized errors
		double last_world_linear_accel_y = 0;

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