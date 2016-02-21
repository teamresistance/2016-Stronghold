package org.teamresistance.auto;

import org.teamresistance.IO;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.auto.states.Target;
import org.teamresistance.auto.states.Shoot;
import org.teamresistance.auto.states.AntlersDown;
import org.teamresistance.auto.states.AntlersUp;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 /*
 * Four states: initial positioning?, defense crossing, tower positioning, targeting/shooting
 */
public class Autonomous extends State {
	//follows the same indexing as the array found in CrossDefense's constructor
	
	private StateMachine autoMachine;
	private StateMachine antlerMachine;
	private StateMachine lifterMachine;
	
	//public Autonomous(StateMachine antlerMachine, StateMachine lifterMachine) {
	public Autonomous() {
		
		//autoMachine.addState(new CrossDefense(defenseType), "CrossDefense");
		//autoMachine.addState(new DriveToTower(defensePosition, goalPosition, (int) defenseType), "DriveToTower");
		
	}

	@Override
	public void onEntry(StateTransition e) {
		//autoMachine.setState("CrossDefense");
		
		autoMachine = new StateMachine();
		antlerMachine = new StateMachine();
		lifterMachine = new StateMachine();

		gripTable = NetworkTable.getTable("GRIP/myContoursReport");

		//AngleMatch target = new AngleMatch();
		//driveModes.addState(target);
		driveModes.addState(new Idle(), "Idle");
		autoMachine.addState(new Shoot());
		autoMachine.addState(new Target());
		driveModes.addState(new AngleHold());
		
		antlerMachine.addState(new AntlersUp());
		antlerMachine.addState(new AntlersDown());
		
		lifterMachine.addState(new LiftPortcullis(IO.lifterTiltSolenoid, IO.bottomLifterSwitch));
		lifterMachine.addState(new MoveLifter("TeleopLifterIdle"));
		lifterMachine.addState(new MoveLifterDown());
		lifterMachine.addState(new MoveLifterUp());
		lifterMachine.addState(new RaiseFlipper());
		//lifterMachine.addState(new TeleopLifterIdle());
		DelayState delayState = new DelayState();
		delayState.setDelay(Constants.LIFTER_PAUSE_TIME);
		lifterMachine.addState(delayState);
		lifterMachine.addState(new TopOutLifter());
		lifterMachine.addState(new LeavePortcullis());
		lifterMachine.addState(new LowerFlipper());
		lifterMachine.addState(new LowerDrawbridge());
		lifterMachine.addState(new DriveThroughDrawbridge());
		
		
		
		
		
		
		
		
	
	}
	
	boolean first = true;
	boolean second = false;
	boolean third = false;
	double defenseType = 1;
	int defensePosition = 3;
	int goalPosition = 0;

	@Override
	public void update() { //Still need to fix: having trouble taking in info at the beginning of the match
		
		if(first == true) {
			first = false;
			second = true;
		}
		
		if(second==true) {
			defenseType = 2;//SmartDashboard.getNumber("Defense Type");
			//SmartDashboard.putNumber("Defense Type Choice", defenseType);
			defensePosition = 3;//(int) SmartDashboard.getNumber("Defense Position");
			//SmartDashboard.putNumber("Chosen position", defensePosition);
			goalPosition = 2;//(int) SmartDashboard.getNumber("Goal Choice");
			//SmartDashboard.putNumber("Chosen goal", goalPosition);
		
			second = false;
			third = true;
		}
		
		if(third== true) {
			autoMachine.addState(new DriveToTower(defensePosition, goalPosition, (int) defenseType), "DriveToTower");
			
			autoMachine.setState("DriveToTower");
		}
		
		
		
		//double test = SmartDashboard.getNumber("Test");
		//SmartDashboard.putNumber("Defense Type Choice", test);
		
		//IO.compressorRelay.set(IO.compressor.enabled() ? Relay.Value.kOn : Relay.Value.kOff);
		
		//if(!IO.imu.isStraight(10, 90)) {
		//	IO.imu.turnTo(90, 10);
		//}
		//SmartDashboard.getData("test number");
		//double test = SmartDashboard.getData("test number");
		//SmartDashboard.putNumber("TestPut", test);
		
		//autoMachine.update(); //comment this in/out to enable movement
		
		
		
	}

	@Override
	public void onExit(StateTransition e) {
		
	}

}
