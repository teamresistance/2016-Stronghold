
package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.state.StateMachine;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private StateMachine robotModes;
	
	@Override
	public void robotInit() {
		IO.init();
		
    	robotModes = new StateMachine();
    	robotModes.addState(Teleop.class, "teleop");
    	robotModes.addState(Autonomous.class, "auto");
    }
    
    @Override
	public void autonomousInit() {
    	
	}

    @Override
    public void autonomousPeriodic() {
    	
    }

    @Override
	public void teleopInit() {
    	
    }
    
    @Override
    public void teleopPeriodic() {
        
    }
}
