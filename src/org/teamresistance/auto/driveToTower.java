
package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class driveToTower extends State {

	final public static double[][] DISTANCES = {
			{0, 30, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0	},
			{0, 0, 0, 0}
	};

	final public static int[][] START_ANGLES = {
			{0, 30, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
	};

	final public static double[][] ANGLES = {
			{1.0, 0.0, 0.0, 0.0},
			{0.0, 0.0, 0.0, 0.0},
			{0.0, 0.0, 0.0, 0.0},
			{0.0, 0.0, 0.0, 0.0}
	};
	final public static int[][] END_ANGLES = {
			{0, -60, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
	};


	private double distance;
	private double angle;

	public driveToTower(int defense, int goal) {
		distance = DISTANCES[defense - 2][goal];
		angle = ANGLES[defense - 2][goal];
	}

	@Override
	public void onEntry(StateTransition e) {

	}

	@Override
	public void update() {

	}

	@Override
	public void onExit(StateTransition e) {

	}

}
