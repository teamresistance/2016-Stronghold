package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.AngleHold;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToGoal extends State {

    public static final double[][] DRIVE_TIMES = {
            {0.0, 1.03, 0},
            {0.50, 0.47, 0},
            {0, 0.09, 0.93},
            {0, 0.25, 0.28}
    };
    public static final double DRIVE_SPEED = -0.65;

    private double driveTime;
    private double startTime;
    private boolean isReversed;
    
    public DriveToGoal (int gate, int goal) {
    	isReversed = isReversed(gate, goal);
        driveTime = DRIVE_TIMES[gate][goal];
        SmartDashboard.putNumber("DriveToGoal time", driveTime);
    }
    
    @Override
    public void onEntry(StateTransition e) {
        startTime = Time.getTime();
    }

    @Override
    public void update() {
        if (Time.getTime() - startTime < driveTime) {
            IO.robotDrive.arcadeDrive(isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED, 0);
        } else {
        	gotoState("Target");
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
