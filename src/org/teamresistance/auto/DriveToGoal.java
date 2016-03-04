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
            {-1, 2.25, 3},
            {-1, 1.2, 0}
    };
    public static final double DRIVE_SPEED = 0.5;

    private double driveTime;
    private double startTime;
    private boolean isReversed;
    private double entryAngle;

    public DriveToGoal (boolean isReversed, int gate, int goal) {
        this.isReversed = isReversed;
        this.driveTime = DRIVE_TIMES[gate][goal];
    }

    @Override
    public void onEntry(StateTransition e) {
    	SmartDashboard.putString("^^^^^^^^^CURRENT STATE:", getName());
        startTime = Time.getTime();
        entryAngle = Math.toRadians(IO.imu.getYaw());
    }

    @Override
    public void update() {
        if (Time.getTime() - startTime < driveTime) {
            // Hold our entry angle to ensure we drive straight
            double currentAngle = Math.toRadians(IO.imu.getYaw());
            double rotateSpeed = AngleHold.calculateAngleCorrection(currentAngle, entryAngle);
            double driveSpeed = isReversed ? -1 * DRIVE_SPEED : DRIVE_SPEED;
            IO.robotDrive.arcadeDrive(driveSpeed, rotateSpeed);
        } else {
        	SmartDashboard.putBoolean("&&&&&&&&&&&&&&&&&&TARGETING", true);
            // TODO target
        }
    }

}
