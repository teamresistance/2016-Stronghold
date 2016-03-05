package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DriveToGoal extends State {

	
    public static final double[][] DRIVE_TIMES = {
            {0, 1.2, -1},
            {3, 2.25, -1},
            {-1, .5, 0},
            {-1, 2.0, 1.0}
    };
    public double DRIVE_SPEED = 0.5;

    private static final boolean[][] REVERSED = {
    	{true, false, false},
    	{true, false, false},
    	{true, false, false},
    	{true, false, false}
    };
    
    private double driveTime;
    private double startTime;
    private boolean isReversed;
    int heading;
    
    public DriveToGoal (int gate, int goal) {
    	isReversed = REVERSED[gate][goal];
        driveTime = DRIVE_TIMES[gate][goal];
    }

    @Override
    public void onEntry(StateTransition e) {
        startTime = Time.getTime();
        heading = (int) IO.imu.getYaw(); // get the initial heading to maintain it while driving to the goal
    }

    @Override
    public void update() {
        if (Time.getTime() - startTime < driveTime) {
            IO.robotDrive.arcadeDrive(isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED, IO.imu.turnTo(heading, AutoConstants.ANGLE_ERROR_THRESHOLD));
        } else {
        	SmartDashboard.putBoolean("&&&&&&&&&&&&&&&&&&TARGETING", true);
            // target
        }
    }

}
