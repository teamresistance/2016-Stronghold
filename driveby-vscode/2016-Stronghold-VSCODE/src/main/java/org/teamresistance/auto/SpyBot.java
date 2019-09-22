package org.teamresistance.auto;

import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.Constants;
import org.teamresistance.IO;

public class SpyBot extends State {

	private double startTime;
	
	public SpyBot() {

	}

	@Override
	public void onEntry(StateTransition e) {
		IO.shooterSolenoid.set(true);
		startTime = Time.getTime();
		
	}

	@Override
	public void update() {
		if(Time.getTime() - startTime >= Constants.SHOOTER_DELAY) {
			IO.shooterSolenoid.set(false);
		} else {
			IO.shooterSolenoid.set(true);
		}
	}
}
