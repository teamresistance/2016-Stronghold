package org.teamresistance.auto;

import org.teamresistance.auto.defense.Defense;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class CrossDefense extends State {

	private final Defense defense;

	public CrossDefense(Defense defense) {
		this.defense = defense;
	}

	@Override
	public void onEntry(StateTransition e) {
		defense.beginCrossing();
	}

	@Override
	public void update() {
		if (defense.isCrossing()) {
			defense.whileCrossing();
		} else {
			if(SmartDashboard.getBoolean("Turn and Shoot", false)) {
				gotoState("DriveToLine");
			}
		}
}


