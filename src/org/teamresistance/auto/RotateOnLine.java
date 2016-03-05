package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class RotateOnLine extends State {

    private final int heading;

    private final static int[] HEADINGS = {150, -90, -150};
    
    public RotateOnLine (int goal) {
        heading = HEADINGS[goal];
        SmartDashboard.putNumber("Goal Angle", heading);
    }

    @Override
    public void onEntry(StateTransition e) {
    	
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
