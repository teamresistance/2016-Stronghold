package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.AngleHold;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DriveToGoal extends State {

    public static final double[][] DRIVE_TIMES = {
            {2, 1.2, -1},
            {3, 2.25, -1},
            {-1, 1, 0},
            {-1, 2.0, 1.0}
    };
    public static final double DRIVE_SPEED = 0.5;

    private double driveTime;
    private double startTime;
    private boolean isReversed;
    int heading; //holds heading for error correction
    
    public DriveToGoal (int gate, int goal, int heading) {
    	//isReversed = REVERSED[gate][goal];
    	isReversed = isReversed(gate, goal);
        driveTime = DRIVE_TIMES[gate][goal];
        this.heading = heading;
    }

    @Override
    public void onEntry(StateTransition e) {
    	SmartDashboard.putString("^^^^^^^^^CURRENT STATE:", getName());
        startTime = Time.getTime();
    }

    @Override
    public void update() {
//        if (Time.getTime() - startTime < driveTime) {
//            IO.robotDrive.arcadeDrive(isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED, IO.imu.turnTo(heading, AutoConstants.ANGLE_ERROR_THRESHOLD));
//        } else {
//        	SmartDashboard.putBoolean("&&&&&&&&&&&&&&&&&&TARGETING", true);
//            // target
//        }
        
        if (Time.getTime() - startTime < driveTime) {
            double driveSpeed = isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED;
            IO.robotDrive.arcadeDrive(driveSpeed, 0);
        } else {
        	SmartDashboard.putBoolean("&&&&&&&&&&&&&&&&&&TARGETING", true);
            // TODO target
        }
    }
    
    private boolean isReversed(int gate, int goal) {
    	if (goal == 0) return false;
    	if (goal == 2) return true;
    	
    	if (gate == 0 || gate == 1) return true;
    	if (gate == 2 || gate == 3) return false;
    	throw new IllegalArgumentException("Illegal DriveToGoal permutation: Gate: " + gate + "; Goal: " + goal);
    }

}
