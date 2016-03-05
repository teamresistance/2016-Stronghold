package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class DriveToLine extends State {

    //[gate][goal] goal = 1 is constant.
    public static final double[][] DRIVE_TIMES = {
            {4, 1, -1},
            {2, 1, -1},
            {-1, 1, 1.95},
            {-1, 1, 1.7}
    };
    public static final double DRIVE_SPEED = 0.5;

    private double driveTime;
    private double startTime;
    private boolean isReversed;

    public DriveToLine(boolean isReversed, int gate, int goal) {
        this.isReversed = isReversed;
        this.driveTime = DRIVE_TIMES[gate][goal];
    }

    @Override
    public void onEntry(StateTransition e) {
        startTime = Time.getTime();
    }

    @Override public void update() {
        if (Time.getTime() - startTime < driveTime) {
            IO.robotDrive.arcadeDrive(isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED, 0);
        } else {
            gotoState("RotateOnLine");
        }
    }
}
