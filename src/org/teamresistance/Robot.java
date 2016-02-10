
package org.teamresistance;

import java.io.IOException;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	
	private StateMachine robotModes;
	
	@Override
	public void robotInit() {
	  try {
            new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		IO.init();
		
    	robotModes = new StateMachine();
    	robotModes.addState(Teleop.class, "teleop");
    	robotModes.addState(Autonomous.class, "auto");
    }
    
    @Override
	public void autonomousInit() {
    	robotModes.setState("auto");
	}

    @Override
    public void autonomousPeriodic() {
    	Time.update();
    	JoystickIO.update();
    	robotModes.update();
    }

    @Override
	public void teleopInit() {
    	robotModes.setState("teleop");
    }
    
    @Override
    public void teleopPeriodic() {
    	Time.update();
        robotModes.update();
    }
}
