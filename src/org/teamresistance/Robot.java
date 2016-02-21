package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	private StateMachine robotModes;
	public static Teleop teleop;
	public static Autonomous auto;
	public static String robotState;
	
	//private StateMachine antlerMachine;
	private StateMachine robotMachine;
//	private StateMachine lifterMachine;
	
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
			auto = new Autonomous(null, null);
		}
		robotModes.addState(auto, "auto");
	
		//antlerMachine = new StateMachine();
//		robotMachine = new StateMachine();
//		
//		robotMachine.addState(new Teleop(), "teleop");
//		robotMachine.addState(new Autonomous(antlerMachine, lifterMachine), "auto");
		
		//antlerMachine.addState(new AntlerSnorflerUp());
		//antlerMachine.addState(new AntlersDown());
		//antlerMachine.addState(new SnorflerDown());
	
	}

	@Override
	public void autonomousInit() {
		robotState = "auto";
		robotModes.setState("auto");
		//antlerMachine.setState("AntlerSnorflerUp");
		//lifterMachine.setState();
	}

	@Override
	public void autonomousPeriodic() {
		Time.update();
		robotMachine.update();
//		antlerMachine.update();
//		lifterMachine.update();
	}

	@Override
	public void teleopInit() {
		robotState = "teleop";
		robotModes.setState("teleop");
	}

	@Override
	public void teleopPeriodic() {
		Time.update();
		JoystickIO.update();
		robotMachine.update();
//		antlerMachine.update();
//		lifterMachine.update();
	}
}

