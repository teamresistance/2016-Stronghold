package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class RotateOnLine extends State {

    private int heading;

    public RotateOnLine (int goal) {
        heading = 210 + goal * 60;
    }

    @Override
    public void onEntry(StateTransition e) {
        //
    }

    @Override
    public void update() {
        if (IO.imu.isStraight(AutoConstants.ANGLE_ERROR_THRESHOLD, heading)) {
            gotoState("DriveToGoal");
        } else {
            IO.robotDrive.arcadeDrive(0, heading);
        }
    }
}
