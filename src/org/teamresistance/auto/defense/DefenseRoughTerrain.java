package org.teamresistance.auto.defense;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.teamresistance.IO;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.Auto_v2;
import org.teamresistance.auto.Defense;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateTransition;

	 /*
	 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class DefenseRoughTerrain extends Defense {
	
	private double time = 0.0;
	
	@Override
	public void beginCrossing() {
		
	}
	
	@Override
	public void whileCrossing() {
		SmartDashboard.putBoolean("Rough Terrain Finished", true);
		IO.robotDrive.arcadeDrive(Auto_v2.crossDefensePower, 0.0);
	}


}