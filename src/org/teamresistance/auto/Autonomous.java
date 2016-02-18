package org.teamresistance.auto;

import org.teamresistance.autostates.AutoAntlersDown;
import org.teamresistance.autostates.AutoAntlersUp;
import org.teamresistance.autostates.CrossDefense;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
 */

public class Autonomous extends State {
	private StateMachine autoStates;
	private StateMachine antlerMachine;
	public static int defensePosition = 0;
	public static int defenseType = 0;
	public static int goalNum = 0;

	public Autonomous() {
		autoStates = new StateMachine();
		antlerMachine = new StateMachine();
	}
	
	@Override
	public void init() {
		autoStates.addState(new CrossDefense(), "CrossDefense");
		autoStates.addState(new AutoTargeting(), "Targeting");
		
		antlerMachine.addState(new AutoAntlersDown(), "AntlersDown");
		antlerMachine.addState(new AutoAntlersUp(), "AntlersUp");
		
		//This may not be done properly, so if the program isn't properly finding the defense position/number/goal this is why
		defensePosition = (int) SmartDashboard.getNumber("position");
		defenseType = (int) SmartDashboard.getNumber("defense type");
		goalNum = (int) SmartDashboard.getNumber("goal");
		

	}

	@Override
	public void onEntry(StateTransition e) {
		autoStates.setState("CrossDefense");
	}

	@Override
	public void update() {
		autoStates.update();
		
	}

	@Override
	public void onExit(StateTransition e) {

	}

}
