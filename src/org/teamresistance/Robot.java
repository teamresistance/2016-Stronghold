package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortcullis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockWall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import org.teamresistance.robostates.DelayState;
import org.teamresistance.robostates.lifter.DriveThroughDrawbridge;
import org.teamresistance.robostates.lifter.LeavePortcullis;
import org.teamresistance.robostates.lifter.LiftPortcullis;
import org.teamresistance.robostates.lifter.LowerDrawbridge;
import org.teamresistance.robostates.lifter.LowerFlipper;
import org.teamresistance.robostates.lifter.MoveLifter;
import org.teamresistance.robostates.lifter.MoveLifterDown;
import org.teamresistance.robostates.lifter.MoveLifterUp;
import org.teamresistance.robostates.lifter.RaiseFlipper;
import org.teamresistance.robostates.lifter.TeleopLifterIdle;
import org.teamresistance.robostates.lifter.TopOutLifter;
import org.teamresistance.teleop.Teleop;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private StateMachine robotModes;
	private StateMachine lifterMachine;

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

		// So the robot knows which crossing strategy to use.
		SendableChooser defenseChooser = new SendableChooser();
		defenseChooser.addObject("Cheval de frise", new DefenseCheval());
		defenseChooser.addObject("Drawbridge", new DefenseDrawbridge(lifterMachine));
		defenseChooser.addObject("Moat", new DefenseMoat());
		defenseChooser.addObject("Portcullis", new DefensePortcullis(lifterMachine));
		defenseChooser.addObject("Ramparts", new DefenseRamparts());
		defenseChooser.addObject("Rock wall", new DefenseRockWall());
		defenseChooser.addObject("Rough terrain", new DefenseRoughTerrain());
		SmartDashboard.putData(">> Autonomous Defense <<", defenseChooser);

		// So the robot knows which path it should take to reach the goal.
		SendableChooser positionChooser = new SendableChooser();
		positionChooser.addObject("Gate 2", 0); // indexes are already normalized
		positionChooser.addObject("Gate 3", 1);
		positionChooser.addObject("Gate 4", 2);
		positionChooser.addObject("Gate 5", 3);
		SmartDashboard.putData(">> Autonomous Robot Position <<", positionChooser);

		// So the robot knows which goal it needs to reach.
		SendableChooser goalChooser = new SendableChooser();
		goalChooser.addObject("Left goal", 0);
		goalChooser.addObject("Middle goal", 1);
		goalChooser.addObject("Right goal", 2);
		SmartDashboard.putData(">> Autonomous Target Goal <<", goalChooser);

		IO.init();
		JoystickIO.init();
		lifterMachine = new StateMachine();

		lifterMachine.addState(new LiftPortcullis(IO.lifterTiltSolenoid, IO.bottomLifterSwitch));
		lifterMachine.addState(new MoveLifter("TeleopLifterIdle"));
		lifterMachine.addState(new MoveLifterDown());
		lifterMachine.addState(new MoveLifterUp());
		lifterMachine.addState(new RaiseFlipper());
		lifterMachine.addState(new TeleopLifterIdle());
		DelayState delayState = new DelayState();
		delayState.setDelay(Constants.LIFTER_PAUSE_TIME);
		lifterMachine.addState(delayState);
		lifterMachine.addState(new TopOutLifter());
		lifterMachine.addState(new LeavePortcullis());
		lifterMachine.addState(new LowerFlipper());
		lifterMachine.addState(new LowerDrawbridge());
		lifterMachine.addState(new DriveThroughDrawbridge(IO.robotDrive, IO.flipperSolenoid));

		robotModes = new StateMachine();
		if(teleop == null) {
			teleop = new Teleop(lifterMachine);
		}
		robotModes.addState(teleop, "teleop");
		if(auto == null) {
			auto = new Autonomous(defenseChooser, positionChooser, goalChooser);
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
		JoystickIO.update();
		robotModes.update();
	}
}
