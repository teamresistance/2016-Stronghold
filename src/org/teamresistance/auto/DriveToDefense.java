package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToDefense extends State {

	final private static double[] DISTANCES = {
		
	};
	
	final private static double DRIVE_TIME = 1.25;
	final private static double DRIVE_SPEED = -0.65; 
	
	private double startTime;
	
	
	public DriveToDefense(int type) {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
	}

	@Override
	public void update() {
		if (Time.getTime() - startTime < DRIVE_TIME) {
			IO.robotDrive.arcadeDrive(DRIVE_SPEED, 0);
		} else {
			gotoState("CrossDefense");
		}
	}
}
