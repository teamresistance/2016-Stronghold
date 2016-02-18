package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.auto.AutoMaster;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.autostates.TowerDrive;
import org.teamresistance.autostates.AutoAntlersDown;
import org.teamresistance.autostates.AutoAntlersUp;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
 */

public class Autonomous extends State {
	StateMachine autoStates;
	StateMachine antlerMachine;
	public static int defensePosition = 0;
	public static int defenseType = 0;
	public static int goalNum = 0;

	protected Autonomous(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
		autoStates = new StateMachine();
		autoStates.addState(CrossDefense.class,"CrossDefense");
		autoStates.addState(AutoTargeting.class, "Targeting");
		antlerMachine = new StateMachine();
		antlerMachine.addState(AutoAntlersDown.class, "AntlersDown");
		antlerMachine.addState(AutoAntlersUp.class, "AntlersUp");
		
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
		driveStates.update();
		
	}

	@Override
	public void onExit(StateTransition e) {

	}

}
