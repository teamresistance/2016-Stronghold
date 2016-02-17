package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.auto.DefenseStates;
import org.teamresistance.defenses.DefenseMaster;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CrossDefense extends State {
	
	protected CrossDefense(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		//SmartDashboard.putNumber("position", 3);
		SmartDashboard.putNumber("defense type", 5);
		//setDefenseType();
		//setDefensePos();
	}

	@Override
	public void onEntry(StateTransition e) {
		//DefenseStates();
		int type = (int) SmartDashboard.getNumber("Defense type");
		switch(type) { //switch order to actually match the order by category
		case 1: DefenseMaster.teeter.cross();
			break;
		case 2: DefenseMaster.drawbridge.cross();
			break;
		case 3: DefenseMaster.moat.cross();
			break;
		case 4: DefenseMaster.portcullis.cross();
			break;
		case 5: DefenseMaster.ramparts.cross();
			break;
		case 6: DefenseMaster.rockWall.cross();
			break;
		case 7: DefenseMaster.roughTerrain.cross();
			break;
		default: DefenseMaster.sallyPort.cross();
			break;
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onExit(StateTransition e) {
		gotoState("DriveToTower");
	}

}
