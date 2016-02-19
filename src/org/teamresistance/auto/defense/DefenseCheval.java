package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Autonomous;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateTransition;

/*
 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
 */

public class DefenseCheval extends Defense {

	private double time = 0.0;

	@Override
	public void beginCrossing() {
		while (!IO.antlerSolenoid.get()) {
			IO.antlerSolenoid.set(true);
		}
	}

	@Override
	public void whileCrossing() {
		time += Time.getDelta();

		if (Time.getDelta() > 0.5) {
			while (IO.antlerSolenoid.get()) {
				IO.antlerSolenoid.set(false);
			}
		} else {
			while(!IO.antlerSolenoid.get()) {
				IO.antlerSolenoid.set(true); // down = true, based on org/teamresistance/robostates/AntlersDown.java. This doesn't use the state machine structure, though, since there's no need for it
			}
		}

		if (IO.navX.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD) && time < 2.0) {
			// don't know if I can do it like this - check to make sure it doesn't freeze up

			IO.robotDrive.arcadeDrive(AutoConstants.CHEVAL_CROSS_SPEED, 0.0);
		} else {
			this.setCrossing(false);
		}

	}
}