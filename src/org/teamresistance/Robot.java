package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private StateMachine robotModes;
	public static Teleop teleop;
	public static Autonomous auto;
	public static String robotState;
	
    final int cheval = 0;
    final int drawbridge = 1;
    final int moat = 2;
    final int portcullis = 3;
    final int ramparts = 4;
    final int rockwall = 5;
    final int roughterrain = 6;
    
    final int leftGoal = 0;
    final int middleGoal = 1;
    final int rightGoal = 1;
    
    final int pos2 = 2;
    final int pos3 = 3;
    final int pos4 = 4;
    final int pos5 = 5;
    
    SendableChooser defenseType;
    SendableChooser goal;
    SendableChooser position;
	//private StateMachine antlerMachine;
	private StateMachine robotMachine;
//	private StateMachine lifterMachine;
	
	@Override
	public void robotInit() {
		SmartDashboard.putNumber("Test", 0.0);
		//SmartDashboard.putNumber("Goal Choice", 0.0);
		//SmartDashboard.putNumber("Defense Position", 0.0);
        
		IO.init();
		JoystickIO.init();

		robotModes = new StateMachine();
		if(teleop == null) {
			teleop = new Teleop();
		}
		robotModes.addState(teleop, "teleop");
		if(auto == null) {
			auto = new Autonomous();
			//auto = new Autonomous(null, null);
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

		JoystickIO.update();
		robotModes.update();
		
		//int defenseTypeChoice = (int) SmartDashboard.getInt("Defense Type");
		//SmartDashboard.putNumber("Choice", defenseTypeChoice);
		
		//antlerMachine.update();
		
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

