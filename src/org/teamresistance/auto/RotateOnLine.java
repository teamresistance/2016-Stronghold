package org.teamresistance.auto;

import org.teamresistance.Constants;
import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateOnLine extends State {

    private final int heading;

    private final static int[] HEADINGS = {-30, -90, -150};
    
    public RotateOnLine (int goal) {
        heading = HEADINGS[goal];
        SmartDashboard.putNumber("RotateOnLine heading", heading);
    }

    @Override
    public void onEntry(StateTransition e) {
    	IO.snorflerMotor.set(Constants.BOULDER_LOAD_SPEED);
    }

    boolean shouldRun = false;
    
    @Override
    public void update() {
        // If the robot is not facing the heading, continue rotating
        if (IO.imu.isStraight(AutoConstants.ANGLE_ERROR_THRESHOLD, heading)) {
        	gotoState("DriveToGoal");
        } else {
            IO.robotDrive.arcadeDrive(0, IO.imu.turnTo(heading, AutoConstants.ANGLE_ERROR_THRESHOLD));
        }
    }
}
