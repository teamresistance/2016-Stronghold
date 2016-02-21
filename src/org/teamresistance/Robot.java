package org.teamresistance;

import org.teamresistance.auto.AutoConstants;
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
	
	//private StateMachine antlerMachine;
	private StateMachine robotMachine;
//	private StateMachine lifterMachine;
	
	@Override
	public void robotInit() {
		//SmartDashboard.putNumber("Defense Type", 0.0);
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
		//AutoConstants.defenseType = (int) SmartDashboard.getNumber("Defense Type");
		//SmartDashboard.putNumber("Defense Type Choice", AutoConstants.defenseType);
		//AutoConstants.defensePosition = (int) SmartDashboard.getNumber("Defense Position");
		//SmartDashboard.putNumber("Chosen position", AutoConstants.defensePosition);
		//AutoConstants.goalPosition = (int) SmartDashboard.getNumber("Goal Choice");
		//SmartDashboard.putNumber("Chosen goal", AutoConstants.goalPosition);
		
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

