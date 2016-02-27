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

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {

	private final StateMachine autoMachine;

	public Autonomous(StateMachine lifterMachine) {
		autoMachine = new StateMachine();
		
		// This may not be done properly, so if the program isn't properly finding the defense position/number/goal this is why
		int fromGatePosition = 5; // position of the defense
		int defenseType = 6;
		int goalPosition = 1;

		// TODO pull numbers from SmartDashboard
		//(int) SmartDashboard.getNumber("defense position");
		//(int) SmartDashboard.getNumber("defense type");
		//(int) SmartDashboard.getNumber("goal");

		Defense defense = defense = new Defense[]{
				new DefenseCheval(),
				new DefenseDrawbridge(lifterMachine),
				new DefenseMoat(),
				new DefensePortcullis(lifterMachine),
				new DefenseRamparts(),
				new DefenseRockWall(),
				new DefenseRoughTerrain()
		}[defenseType];
		
		autoMachine.addState(new DriveToDefense(), "DriveToDefense");
		autoMachine.addState(new CrossDefense(defense), "CrossDefense");
		autoMachine.addState(new DriveToTower(defense, fromGatePosition, goalPosition), "DriveToTower");
	}

	@Override
	public void onEntry(StateTransition e) {
		// Drive to the defense
		autoMachine.setState("DriveToDefense");
	}

	@Override
	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		//IO.imu.turnTo(90, 5);

		autoMachine.update();
	}

}
