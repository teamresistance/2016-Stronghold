package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import java.io.IOException;


public class Robot{

	public void robotInit() {
		try {
			new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IO.init();
		JoystickIO.init();

		StateMachine robotMachine = new StateMachine();
		StateMachine autoMachine1 = new StateMachine();
		
		robotMachine.addState(new Teleop(), "teleop");
		robotMachine.addState(new Autonomous(), "auto");
		
	}

	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		Time.update();
	}

	public void teleopInit() {
	}

	public void teleopPeriodic() {
		Time.update();
		JoystickIO.update();
	}
}
