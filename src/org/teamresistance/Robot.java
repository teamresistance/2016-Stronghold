package org.teamresistance;

import java.io.IOException;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.robostates.AntlerSnorflerUp;
import org.teamresistance.robostates.AntlersDown;
import org.teamresistance.robostates.SnorflerDown;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	private StateMachine robotMachine;
	private StateMachine antlerMachine;
	
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
		antlerMachine = new StateMachine();
		
		robotMachine.addState(new Teleop(), "teleop");
		robotMachine.addState(new Autonomous(), "auto");
		
		antlerMachine.addState(new AntlerSnorflerUp());
		antlerMachine.addState(new AntlersDown());
		antlerMachine.addState(new SnorflerDown());
	}

	@Override
	public void autonomousInit() {
		robotMachine.setState("auto");
		antlerMachine.setState("AntlerSnorflerUp");
	}

	@Override
	public void autonomousPeriodic() {
		Time.update();
		robotMachine.update();
		antlerMachine.update();
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
		antlerMachine.update();
	}
}
