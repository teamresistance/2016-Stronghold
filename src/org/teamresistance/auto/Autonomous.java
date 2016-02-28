package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Autonomous extends State {

	private final StateMachine autoMachine;
	private final SendableChooser defenseChooser;
	private final SendableChooser positionChooser;
	private final SendableChooser goalChooser;

	public Autonomous(SendableChooser defenseChooser, SendableChooser positionChooser,
					  SendableChooser goalChooser) {
		this.autoMachine = new StateMachine();
		this.defenseChooser = defenseChooser;
		this.positionChooser = positionChooser;
		this.goalChooser = goalChooser;
	}

	@Override
	public void onEntry(StateTransition e) {
		Defense defense = (Defense) defenseChooser.getSelected();   // defense to cross
		int gate = (int) positionChooser.getSelected();             // gate we're driving through
		int goal = (int) goalChooser.getSelected();                 // goal we're targeting

		autoMachine.addState(new DriveToDefense(defense.isReversed()), "DriveToDefense");
		autoMachine.addState(new CrossDefense(defense), "CrossDefense");
		autoMachine.addState(new DriveToLine(defense.isReversed(), gate, goal), "DriveToLine");
		autoMachine.addState(new RotateOnLine(goal), "RotateOnLine");
		autoMachine.addState(new DriveToGoal(gate, goal), "DriveToGoal");

		// Drive to the defense
		autoMachine.setState("DriveToDefense");
	}

	@Override
	public void update() {
		IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		autoMachine.update();
	}

}
