package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortcullis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockWall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends State {

	private StateMachine autoMachine;

	public Autonomous(StateMachine lifterMachine) {
		autoMachine = new StateMachine();

		// This may not be done properly, so if the program isn't properly finding the defense position/number/goal this is why
		int gate = 1; // position of the defense
		int defenseType = 0;
		int goal = 1;

		// TODO pull numbers from SmartDashboard
		//(int) SmartDashboard.getNumber("defense position") - 2;
		//(int) SmartDashboard.getNumber("defense type");
		//(int) SmartDashboard.getNumber("goal");

//		Defense defense = new Defense[]{
//				//new DefenseCheval(),
//				//new DefenseDrawbridge(lifterMachine),
//				//new DefenseMoat(),
//				//new DefensePortcullis(lifterMachine),
//				//new DefenseRamparts(),
//				//new DefenseRockWall(),
//				new DefenseRoughTerrain()
//		}[defenseType];
		Defense defense = new Defense(){

			@Override
			public boolean isReversed() {
				return !false;
			}

			@Override
			public void whileCrossing() {
				setCrossing(false);
			}};

		autoMachine.addState(new DriveToDefense(defense.isReversed()), "DriveToDefense");
		autoMachine.addState(new CrossDefense(defense), "CrossDefense");
		autoMachine.addState(new DriveToLine(defense.isReversed(), gate, goal), "DriveToLine");
		autoMachine.addState(new RotateOnLine(goal), "RotateOnLine");
		autoMachine.addState(new DriveToGoal(gate, goal), "DriveToGoal");

	}

	@Override
	public void onEntry(StateTransition e) {
		// Drive to the defense
		autoMachine.setState("DriveToDefense");
	}

	@Override
	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
	//	IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		 SmartDashboard.putNumber("Yaw", IO.imu.getYaw());
		autoMachine.update();
	}

}
