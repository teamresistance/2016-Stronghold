package org.teamresistance.util.state;
/**
 * Represents an abstract state to be used by a StateMachine. 
 * An instance of State represents the state of the machine on a continuous interval of time, 
 * during which, the machine did not change state.
 * 
 * 
 * @author Mathis
 *
 */
public abstract class State {
	
	private StateMachine stateMachine;

	protected State(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	/**
	 * Sets the state of this State's state machine to an instance with the specified name.
	 * @param stateName the name of the state
	 * @return true if and only if the state of the state machine was changed.
	 */
	final protected boolean gotoState(String stateName) {
		return stateMachine.setState(stateName);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
	/**
	 * Called by the state machine when this state is first created.
	 * Contains state object initialization code.
	 */
	public abstract void init();
	
	/**
	 * Called by the state machine whenever this state is entered.
	 * Contains state reset code.
	 * @param e State Transition event object
	 */
	public abstract void onEntry(StateTransition e);
	/**
	 * called by the state machine whenever this state is updated.
	 */
	public abstract void update();
	/**
	 * Called by the state machine whenever this state is exited.
	 * @param e State Transition event object
	 */
	public abstract void onExit(StateTransition e);
}
