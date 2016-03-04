package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class RotateOnLine extends State {

    private final int heading;

    public RotateOnLine (int goal) {
        heading = (210 + goal * 60) % 180 - 180;
    }

    @Override
    public void onEntry(StateTransition e) {
    	SmartDashboard.putString("^^^^^^^^^CURRENT STATE:", getName());
    }

    @Override
    public void update() {
        // If the robot is not facing the heading, continue rotating
        if (!IO.imu.isStraight(AutoConstants.ANGLE_ERROR_THRESHOLD, heading)) {
            IO.robotDrive.arcadeDrive(0, IO.imu.turnTo(heading, AutoConstants.ANGLE_ERROR_THRESHOLD));
        } else {
            // Otherwise, drive along the line to the goal
            gotoState("DriveToGoal");
        }
    }
}
