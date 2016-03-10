package org.teamresistance.auto.manual;

import org.teamresistance.util.Time;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimingReporter implements ManualAutonomous.Listener {

    public String runningState;
    public double entryTime;

    @Override
    public void onStateEntered(String newState) {
        if (runningState != null) {
            double elapsedTime = Time.getTime() - entryTime;
            report(runningState, elapsedTime);
        }

        runningState = newState;
        entryTime = Time.getTime();
    }

    @Override
    public void onFinished() {
        // Get the elapsed time of the final state before finishing
        double elapsedTime = Time.getTime() - entryTime;
        report(runningState, elapsedTime);
    }

    private void report(String runningState, double elapsedTime) {
        SmartDashboard.putNumber("Auto Timing [" + runningState + "]", elapsedTime);
    }

}
