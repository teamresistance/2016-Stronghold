package org.teamresistance.autostates;

import org.teamresistance.IO;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.auto.AutoConstants;
import org.teamresistance.auto.AutoMaster;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;
import org.teamresistance.IO;
import org.teamresistance.JoystickIO;
import org.teamresistance.robostates.AntlersDown;

	 /*
	 * Four states: initial positioning, defense crossing, tower positioning, targeting/shooting
	 */

public class CrossCheval extends State {
	
	private double time = 0.0;
	
	protected CrossCheval(StateMachine stateMachine, String name) {
		super(stateMachine, name);
	}

	@Override
	public void init() {
			
	}

	@Override
	public void onEntry(StateTransition e) {
		//may need to drive forward before lowering antlers?
		gotoState("AntlersDown");
		//drive forward for est half second
		//raise antlers
		//drive forward until level
		
		
		//switch to driving state
		
	}

	@Override
	public void update() {	
		time += Time.getDelta();
		
		if(Time.getDelta()>0.5) {
			gotoState("AntlersUp");
		}
		
		if(!DefenseMaster.imu.isLevel(0, 0, AutoConstants.ANGLE_ERROR_THRESHOLD) && time<2.0) {
			//don't know if I can do it like this - check to make sure it doesn't freeze up
			
			IO.robotDrive.arcadeDrive(AutoConstants.CHEVAL_CROSS_SPEED, 0.0);
		}
		else {
			
			//turn
			//drive
			//turn
			AutoMaster.driver.drive();
			
		}
		
	}

	@Override
	public void onExit(StateTransition e) {
		gotoState("Targeting");
	}


}