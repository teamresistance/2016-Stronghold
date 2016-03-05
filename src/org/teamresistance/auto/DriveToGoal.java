package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.teleop.driveModes.AngleHold;
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
    	SmartDashboard.putString("^^^^^^^^^CURRENT STATE:", getName());
        startTime = Time.getTime();
        entryAngle = IO.imu.getYaw();
    }

    @Override
    public void update() {
        if (Time.getTime() - startTime < driveTime) {
            // Hold our entry angle to ensure we drive straight
            double currentAngle = IO.imu.getYaw();
            double rotateSpeed = AngleHold.calculateAngleCorrection(currentAngle, entryAngle);
            double driveSpeed = isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED;
            IO.robotDrive.arcadeDrive(driveSpeed, rotateSpeed);
        } else {
        	SmartDashboard.putBoolean("&&&&&&&&&&&&&&&&&&TARGETING", true);
            // TODO target
        }
    }

}
