package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToLine extends State {

    //[gate][goal] goal = 1 is constant.
    private static final double[][] DRIVE_TIMES = {
            {4, 3, -1},
            {2, 3, -1},
            {-1, 3, 2},
            {-1, 3, 4}
    };
    private static final double DRIVE_SPEED = 0.5;

    private boolean isReversed;
    private double driveTime;
    private double startTime;

    public DriveToLine(boolean isReversed, int gate, int goal) {
        this.isReversed = isReversed;
        driveTime = DRIVE_TIMES[gate][goal];
    }

    @Override
    public void onEntry(StateTransition e) {
    	SmartDashboard.putString("^^^^^^^^^CURRENT STATE:", getName());
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
