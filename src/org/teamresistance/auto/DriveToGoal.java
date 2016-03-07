package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.defense.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DriveToGoal extends State {

    public static final double[][] DRIVE_TIMES = {
            {2, 1.2, -1},
            {3, 2.25, -1},
            {2, 2, 1},
            {2, 2.0, 1}
    };
    public static final double DRIVE_SPEED = 0.5;

    private double driveTime;
    private double startTime;
    private boolean isReversed; // TODO rename to avoid confusion
    
    public DriveToGoal(int gate, int goal) {
    	this.isReversed = isReversed(gate, goal);
        this.driveTime = DRIVE_TIMES[gate][goal];
    }

    @Override
    public void onEntry(StateTransition e) {
        startTime = Time.getTime();
    }

    @Override
    public void update() {
        if (Time.getTime() - startTime < driveTime) {
            double driveSpeed = isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED;
            IO.robotDrive.arcadeDrive(driveSpeed, 0);
        } else {
        	SmartDashboard.putBoolean("&&&&&&&&&&&&&&&&&&TARGETING", true);
        }
    }

    /**
     * Something about the relationship between the shooter position and goal. Brandon help pls.
     * Importantly, not to be confused with {@link Defense} reversal.
     * @param gate the gate of the {@link Defense} we're crossing
     * @param goal the goal we're aiming at
     * @return TODO
     */
    private static boolean isReversed(int gate, int goal) {
    	if (goal == 0) return false;
    	if (goal == 2) return true;
    	
    	if (gate == 0 || gate == 1) return true;
    	if (gate == 2 || gate == 3) return false;

        String err = "Illegal DriveToGoal permutation: Gate: " + gate + "; Goal: " + goal;
        throw new IllegalArgumentException(err);
    }
}
