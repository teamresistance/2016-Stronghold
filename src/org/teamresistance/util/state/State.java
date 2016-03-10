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
	
	protected StateMachine stateMachine;
	protected String name;

	protected State() {
		this.name = this.getClass().getSimpleName();
	}

	void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	/**
	 * Sets the state of this State's state machine to an instance with the specified name.
	 * @param stateName the name of the state
	 */
	final protected void gotoState(String stateName) {
		stateMachine.setState(stateName);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Called by the state machine whenever this state is entered.
	 * Contains state reset code.
	 * @param e State Transition event
	 */
	public abstract void onEntry(StateTransition e);

	/**
	 * Called by the state machine whenever this state is updated.
	 */
	public void update() {
		//
	}

	/**
	 * Called by the state machine whenever this state is exited.
	 * @param e State Transition event
	 */
	public void onExit(StateTransition e) {
		//
	}

}