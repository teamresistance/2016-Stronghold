package org.teamresistance.auto.defense;

import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;

/*
 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
 */

public class DefenseCheval extends Defense {

	private static final double CROSS_SPEED = .5;
	
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

		/*if (Time.getDelta() > 0.5) {
			while (IO.antlerSolenoid.get()) {
				IO.antlerSolenoid.set(false);
			}
		} else {*/
			while(!IO.antlerSolenoid.get()) {
				IO.antlerSolenoid.set(true); // down = true, based on org/teamresistance/robostates/AntlersDown.java. This doesn't use the state machine structure, though, since there's no need for it
			}
		//}

		if (IO.imu.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD) && time < 2.0) {
			// don't know if I can do it like this - check to make sure it doesn't freeze up

			IO.robotDrive.arcadeDrive(CROSS_SPEED, 0.0);
		} else {
			while(IO.antlerSolenoid.get()) {
				IO.antlerSolenoid.set(false); //try leaving the antlers down for the full crossing - this worked really well when Robert tried it
			}
			this.setCrossing(false);
		}

	}
}