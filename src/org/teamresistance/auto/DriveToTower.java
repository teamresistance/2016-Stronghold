
package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

public class DriveToTower extends State {

	final private static double[][] DISTANCES = {
		{},
		{},
		{},
		{}
	};
	final private static double[][] ANGLES = {
		{},
		{},
		{},
		{}
	};
	
	private double distance;
	private double angle;
	
	public DriveToTower(int defense, int goal) {
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
