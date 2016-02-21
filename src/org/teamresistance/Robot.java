package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	private StateMachine robotModes;
	public static Teleop teleop;
	public static Autonomous auto;
	public static String robotState;
	
	@Override
	public void robotInit() {
		try {
			new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IO.init();
		JoystickIO.init();

		robotModes = new StateMachine();
		if(teleop == null) {
			teleop = new Teleop();
		}
		robotModes.addState(teleop, "teleop");
		if(auto == null) {
			// TODO properly instantiate Autonomous?
			auto = new Autonomous(null, null);
		}
		robotModes.addState(auto, "auto");
	}

	@Override
	public void autonomousInit() {
		robotState = "auto";
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
		robotState = "teleop";
		robotModes.setState("teleop");
	}

	@Override
	public void teleopPeriodic() {
		Time.update();
		robotModes.update();
	}
}
