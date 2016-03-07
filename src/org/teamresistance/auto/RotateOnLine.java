package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.io.NavXIMU;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

class RotateOnLine extends State {

    public static final int[] HEADINGS = {-30, -90, -150};
    private final int heading;

    public RotateOnLine(int goal) {
        heading = HEADINGS[goal];
    }

    @Override
    public void onEntry(StateTransition e) {
    	//
    }

    @Override
    public void update() {
        // If the robot is already matching the heading, drive to the goal
        if (IO.imu.isStraight(NavXIMU.ANGLE_ERROR_THRESHOLD, heading)) {
        	gotoState("DriveToGoal");
        } else {
            // Otherwise, rotate to the heading
            IO.robotDrive.arcadeDrive(0, IO.imu.turnTo(heading, NavXIMU.ANGLE_ERROR_THRESHOLD));
        }
    }
}
