package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.auto.defense.Defense;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends State {

	private final StateMachine autoMachine;

	private final Defense defense;
	private final int gate;
	private final int goal;

	/**
	 * The Autonomous state.
	 * @param defense the {@link Defense} that will be crossed
	 * @param gate the gate of the defense (also thus the starting position of the robot)
	 * @param goal the goal being targeted
     */
	public Autonomous(Defense defense, int gate, int goal) {
        if (gate < 0 || 4 < gate) {
			throw new IllegalArgumentException("Gate must be between 0 and 4, not " + gate);
		}
        if (goal < 0 || 3 < gate) {
			throw new IllegalArgumentException("Goal must be between 0 and 2, not " + goal);
		}

		this.autoMachine = new StateMachine();
		this.defense = defense;
		this.gate = gate;
		this.goal = goal;
	}

	@Override
	public void onEntry(StateTransition e) {
		// We'll invert the drive speeds if the robot is reversed
		boolean isReversed = defense.isReversed();

		autoMachine.addState(new DriveToDefense(isReversed), "DriveToDefense");
		autoMachine.addState(new CrossDefense(defense), "CrossDefense");
		autoMachine.addState(new DriveToLine(isReversed, gate, goal), "DriveToLine");
		RotateOnLine rotateOnLine = new RotateOnLine(goal);
		autoMachine.addState(rotateOnLine, "RotateOnLine");
		int heading = rotateOnLine.heading();
		autoMachine.addState(new DriveToGoal(gate, goal, heading), "DriveToGoal");
		
		// Drive to the defense
		autoMachine.setState("DriveToDefense");
	}

	@Override
	public void update() {
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		SmartDashboard.putNumber("Yaw", IO.imu.getYaw());
		SmartDashboard.putString("^^^^^^^^^CURRENT AUTO STATE:", autoMachine.getCurrentState());
		autoMachine.update();
	}

}
