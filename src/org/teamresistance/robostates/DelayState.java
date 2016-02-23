package org.teamresistance.robostates;

import org.teamresistance.util.Time;
import org.teamresistance.util.state.ReturnState;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DelayState extends ReturnState {

	private double delay;
	private double startTime;
	
	public DelayState() {
		super();
	}
	
	public DelayState(String returnState) {
		super(returnState);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void onEntry(StateTransition e) {
		startTime = Time.getTime();
		SmartDashboard.putNumber("Delay", 0);
	}

	@Override
	public void update() {
		SmartDashboard.putNumber("Delay", Time.getTime() - startTime);
		if(Time.getTime() - startTime >= delay) {
			gotoReturnState();
		}
	}

	@Override
	public void onExit(StateTransition e) {
		
	}
	
	public void setDelay(double time) {
		this.delay = time;
	}
	
	public double getDelay() {
		return delay;
	}
}
