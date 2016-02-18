package org.teamresistance.autostates;

import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CrossDefense extends State {
	StateMachine defenseMachine;
	
	private final int CHEVAL_DE_FRISE = 0;
	private final int DRAWBRIDGE = 1;
	private final int MOAT = 2;
	private final int PORTCULLIS = 3;
	private final int RAMPARTS = 4;
	private final int ROCKWALL = 5;
	private final int ROUGH_TERRAIN = 6;
	
	protected CrossDefense(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		defenseMachine = new StateMachine();
		defenseMachine.addState(CrossCheval.class, "Cheval");
		defenseMachine.addState(CrossDrawbridge.class, "Drawbridge");
		defenseMachine.addState(CrossMoat.class, "Moat");
		defenseMachine.addState(CrossPortcullis.class, "Portcullis");
		defenseMachine.addState(CrossRamparts.class, "Ramparts");
		defenseMachine.addState(CrossRockWall.class, "RockWall");
		defenseMachine.addState(CrossRoughTerrain.class, "Terrain");
		
	}

	@Override
	public void onEntry(StateTransition e) {
		//DefenseStates();
		int type = (int) SmartDashboard.getNumber("Defense type");
		switch(type) { //switch order to actually match the order by category
		case CHEVAL_DE_FRISE: gotoState("Cheval");
			break;
		case DRAWBRIDGE: gotoState("Drawbridge");
			break;
		case MOAT: gotoState("Moat");
			break;
		case PORTCULLIS: gotoState("Portcullis");
			break;
		case RAMPARTS: gotoState("Ramparts");
			break;
		case ROCKWALL: gotoState("RockWall");
			break;
		default: gotoState("Terrain");
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
