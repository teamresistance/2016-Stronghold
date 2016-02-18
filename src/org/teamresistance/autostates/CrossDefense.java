package org.teamresistance.autostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CrossDefense extends State {
	
	protected CrossDefense(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
	}

	@Override
	public void onEntry(StateTransition e) {
		//DefenseStates();
		int type = (int) SmartDashboard.getNumber("Defense type");
		switch(type) { //switch order to actually match the order by category
		case 0: gotoState("CrossCheval");
			break;
		case 1: gotoState("CrossDrawbridge");
			break;
		case 2: gotoState("CrossMoat");
			break;
		case 3: gotoState("CrossPortcullis");
			break;
		case 4: gotoState("CrossRamparts");
			break;
		case 5: gotoState("CrossRockWall");
			break;
		default: gotoState("CrossRoughTerrain");
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
