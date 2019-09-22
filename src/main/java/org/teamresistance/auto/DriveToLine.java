package org.teamresistance.auto;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.AngleHold;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToLine extends State {

    //[gate][goal] goal = 1 is constant.
    public static final double[][] DRIVE_TIMES = {
            {2.25, 1.17, 0},
            {0.82, 1.17, 0},
            {0, 1.17, 0.50},
            {0, 1.17, 1.20}
    };
    public static final double DRIVE_SPEED = -0.65;

    private double driveTime;
    private double startTime;
    private boolean isReversed;

    public DriveToLine(boolean isReversed, int gate, int goal) {
        this.isReversed = isReversed;
        this.driveTime = DRIVE_TIMES[gate][goal];
        SmartDashboard.putNumber("DriveToLine time", driveTime);
    }

    @Override
    public void onEntry(StateTransition e) {
        startTime = Time.getTime();
    }

    @Override 
    public void update() {
    	IO.antlerSolenoid.set(false);
        if (Time.getTime() - startTime < driveTime) {
            double driveSpeed = isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED;
            IO.robotDrive.arcadeDrive(driveSpeed, 0);
		} else {
			gotoState("RotateOnLine");
		}
    }
}
