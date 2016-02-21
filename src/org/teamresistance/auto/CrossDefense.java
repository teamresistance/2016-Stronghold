package org.teamresistance.auto;

import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortcullis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockWall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

public class CrossDefense extends State {

	private Defense defense;
	
	public CrossDefense(int type, StateMachine antlerMachine, StateMachine lifterMachine) {
		defense = new Defense[]{
			new DefenseCheval(),
			new DefenseDrawbridge(),
			new DefenseMoat(),
			new DefensePortcullis(lifterMachine),
			new DefenseRamparts(),
			new DefenseRockWall(),
			new DefenseRoughTerrain()
		}[type]; //sneaky syntax: indexing into an anonymous array.
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

	@Override
	public void onExit(StateTransition e) {

	}

}
