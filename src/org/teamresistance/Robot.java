package org.teamresistance;

import org.teamresistance.auto.Autonomous;
import org.teamresistance.auto.defense.Defense;
import org.teamresistance.auto.defense.DefenseCheval;
import org.teamresistance.auto.defense.DefenseDrawbridge;
import org.teamresistance.auto.defense.DefenseMoat;
import org.teamresistance.auto.defense.DefensePortcullis;
import org.teamresistance.auto.defense.DefenseRamparts;
import org.teamresistance.auto.defense.DefenseRockWall;
import org.teamresistance.auto.defense.DefenseRoughTerrain;
import org.teamresistance.auto.defense.ReversedRoughTerrain;
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
import org.teamresistance.util.SwingDetection;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;

import java.io.IOException;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private StateMachine robotModes;

	public static Teleop teleop;
	public static String robotState;

	// For on-the-fly Autonomous configurations
	private SendableChooser defenseChooser;		// to know which crossing strategy to use
	private SendableChooser gateChooser;		// to know which path to the goal should be taken
	private SendableChooser goalChooser;		// to know which goal to reach

	@Override
	public void robotInit() {
		try {
			new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		defenseChooser = new SendableChooser();
		gateChooser = new SendableChooser();
		goalChooser = new SendableChooser();

		IO.init();
		JoystickIO.init();
		StateMachine lifterMachine = new StateMachine();
		robotModes = new StateMachine();

		defenseChooser.addDefault("Cheval de frise", new DefenseCheval());
		defenseChooser.addObject("Drawbridge", new DefenseDrawbridge(lifterMachine));
		defenseChooser.addObject("Moat", new DefenseMoat());
		defenseChooser.addObject("Portcullis", new DefensePortcullis(lifterMachine));
		defenseChooser.addObject("Ramparts", new DefenseRamparts());
		defenseChooser.addObject("Rock wall", new DefenseRockWall(new SwingDetection(IO.imu)));
		defenseChooser.addObject("Rough terrain", new DefenseRoughTerrain());
		defenseChooser.addObject("Reversed Rough Terrain", new ReversedRoughTerrain());
		SmartDashboard.putData(">> Autonomous Defense <<", defenseChooser);

		gateChooser.addDefault("Gate 2", 0); // indexes are already normalized
		gateChooser.addObject("Gate 3", 1);
		gateChooser.addObject("Gate 4", 2);
		gateChooser.addObject("Gate 5", 3);
		SmartDashboard.putData(">> Autonomous Robot Position <<", gateChooser);

		goalChooser.addDefault("Left goal", 0);
		goalChooser.addObject("Middle goal", 1);
		goalChooser.addObject("Right goal", 2);
		SmartDashboard.putData(">> Autonomous Target Goal <<", goalChooser);

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

		if (teleop == null) {
			teleop = new Teleop(lifterMachine);
		}
		robotModes.addState(teleop, "teleop");
	}

	@Override
	public void autonomousInit() {
		// "Lock in" the SendableChooser choices at the start of Autonomous
		Defense defense = (Defense) defenseChooser.getSelected();
		int goal = (int) goalChooser.getSelected();
		int gate = (int) gateChooser.getSelected();

		SmartDashboard.putNumber("Goal chosen", goal);
		SmartDashboard.putNumber("Gate chosen", gate + 2);
		SmartDashboard.putString("Defense type", defense.toString());

		// For properly calculating angles when the robot is reversed
		IO.imu.setReversed(defense.isReversed());

		// Instantiate Autonomous with the chosen values
		Autonomous autonomous = new Autonomous(defense, gate, goal);

		robotState = "auto";
		robotModes.addState(autonomous, "auto");
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
