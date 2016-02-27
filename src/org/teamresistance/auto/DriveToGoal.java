package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveToGoal extends State {

    private static final double[][] DRIVE_TIMES = {
            {0, 3, -1},
            {3, 1.5, -1},
            {-1, 1.5, 3},
            {-1, 3, 0}
    };

    private static final double DRIVE_SPEED = 0.5;

    private double driveTime;
    private double startTime;
    private boolean isReversed;

    public DriveToGoal (int gate, int goal) {
        isReversed = isReversed(gate);
        driveTime = DRIVE_TIMES[gate][goal];
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
            // target
        }
    }

    private static boolean isReversed(int gate) {
        return gate == 0 || gate == 1;
    }
}
