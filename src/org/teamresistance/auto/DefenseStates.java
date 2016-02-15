package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.defenses.ChevalDeFrise;
import org.teamresistance.defenses.DefenseMaster;

public class DefenseStates extends State {

	protected DefenseStates(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {

	}

	@Override
	public void onEntry(StateTransition e) {
		AutonomousDefenses def = new AutonomousDefenses();
		//get info from SmartDashboard
		int defenseType = def.getDefenseType(); //replace with this with data from SmartDashbaor
		//go to a state for crossing the particular type of defense
		switch(defenseType) {
			case 1: DefenseMaster.portcullis.cross();
				break;
			case 2: DefenseMaster.teeter.cross();
				break;
			case 3: DefenseMaster.moat.cross();
				break;
			case 4: DefenseMaster.ramparts.cross();
				break;
			case 5: DefenseMaster.drawbridge.cross();
				break;
			case 6: DefenseMaster.sallyPort.cross();
				break;
			case 7: DefenseMaster.ramparts.cross();
				break;
			default: DefenseMaster.roughTerrain.cross();
				break;
				
		}
	}
	

	@Override
	public void update() {

	}

	@Override
	public void onExit(StateTransition e) {

	}

}
