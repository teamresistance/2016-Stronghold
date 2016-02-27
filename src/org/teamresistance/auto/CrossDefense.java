package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class CrossDefense extends State {

	private final Defense defense;

	public CrossDefense(Defense defense) {
		this.defense = defense;
	}

	@Override
	public void onEntry(StateTransition e) {
		defense.setCrossing(true);
		defense.beginCrossing();
	}

	@Override
	public void update() {
		if (defense.isCrossing()) {
			defense.whileCrossing();
		} else {
			gotoState("DriveToTower");
		}
	}

}
