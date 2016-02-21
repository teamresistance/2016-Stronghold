package org.teamresistance;

import java.io.IOException;


public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		try {
			new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IO.init();
		JoystickIO.init();
		
		robotMachine = new StateMachine();
		autoMachine1.0 = new StateMachine();
		
		robotMachine.addState(new Teleop(), "teleop");
		robotMachine.addState(new Autonomous(), "auto");
		
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Time.update();
		robotMachine.update();
	}

	@Override
	public void teleopInit() {
		robotMachine.setState("teleop");
	}

	@Override
	public void teleopPeriodic() {
		Time.update();
		JoystickIO.update();
		robotMachine.update();
	}
}
