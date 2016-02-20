package org.teamresistance;

import java.io.IOException;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.robostates.AntlerSnorflerUp;
import org.teamresistance.robostates.AntlersDown;
import org.teamresistance.robostates.SnorflerDown;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	private StateMachine robotModes;
	public static Teleop teleop;
	public static Autonomous auto;
	public static String robotState;
	
	//private StateMachine antlerMachine;
	
	@Override
	public void robotInit() {
		
		IO.init();
		JoystickIO.init();

		robotModes = new StateMachine();
		if(teleop == null) {
			teleop = new Teleop();
		}
		robotModes.addState(teleop, "teleop");
		if(auto == null) {
			auto = new Autonomous();
		}
		robotModes.addState(auto, "auto");
	
		//antlerMachine = new StateMachine();
		
		//antlerMachine.addState(new AntlerSnorflerUp());
		//antlerMachine.addState(new AntlersDown());
		//antlerMachine.addState(new SnorflerDown());
	
	}

	@Override
	public void autonomousInit() {
		robotState = "auto";
		robotModes.setState("auto");
		//antlerMachine.setState("AntlerSnorflerUp");
	}

	@Override
	public void autonomousPeriodic() {
		Time.update();
		JoystickIO.update();
		robotModes.update();
		
		//antlerMachine.update();
		
	}

	@Override
	public void teleopInit() {
		robotState = "teleop";
		robotModes.setState("teleop");
	}

	@Override
	public void teleopPeriodic() {
		//Time.update();
		//robotModes.update();
				//JoystickIO.update();
				//robotMachine.update();
				//antlerMachine.update();
	}
}

