package org.teamresistance.auto;

import org.teamresistance.JoystickIO;
import org.teamresistance.auto.defense.Defense;
import org.teamresistance.auto.manual.ManualAutonomous;
import org.teamresistance.auto.manual.TimingReporter;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Autonomous extends State {

	private final StateMachine autoMachine;

	private final Defense defense;
	private final int gate;
	private final int goal;
	private final boolean manual;

	/**
	 * The Autonomous state.
	 * @param defense the {@link Defense} that will be crossed
	 * @param gate the gate of the defense (also thus the starting position of the robot)
	 * @param goal the goal being targeted
	 * @param manual true if running in {@link ManualAutonomous}
	 */
	public Autonomous(Defense defense, int gate, int goal, boolean manual) {
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
		this.manual = manual;
	}

	@Override
	public void onEntry(StateTransition e) {
		// We'll invert the drive speeds if the robot is reversed
		boolean isReversed = defense.isReversed();

		// Use an ordered HashMap to represent a sequence
		Map<State, String> states = new LinkedHashMap<>();
		states.put(new DriveToDefense(isReversed), "DriveToDefense");
		states.put(new CrossDefense(defense), "CrossDefense");
		states.put(new DriveToLine(isReversed, gate, goal), "DriveToLine");
		states.put(new RotateOnLine(goal), "RotateOnLine");
		states.put(new DriveToGoal(gate, goal), "DriveToGoal");

		// If we're in manual calibration mode
		if (manual) {
			List<State> stateSequence = new ArrayList<>(states.keySet());
			ManualAutonomous manualAutonomous = new ManualAutonomous(stateSequence, new TimingReporter());
			JoystickIO.btnAngleHold.addPressListener(manualAutonomous); // trigger = the angle hold button
			autoMachine.addState(manualAutonomous, "ManualAutonomous");
			autoMachine.setState("ManualAutonomous");
		} else {
			// Otherwise, register each state like normal
			states.entrySet().forEach(entry -> autoMachine.addState(entry.getKey(), entry.getValue()));

			// Drive to the defense
			autoMachine.setState("DriveToDefense");
		}
	}

	@Override
	public void update() {
		// TODO Remember to uncomment this in the production bot
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		autoMachine.update();
	}
}
