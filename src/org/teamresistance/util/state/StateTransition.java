package org.teamresistance.util.state;

public class StateTransition {
	private State initialState;
	private State newState;
	
	StateTransition(State initialState, State newState) {
		this.initialState = initialState;
		this.newState = newState;
	}

	/**
	 * Returns the state object that the state machine was in previous to this state transition,
	 * or null, if the state machine was not initialized at that time.
	 * @return
	 */
	public State getInitialState() {
		return initialState;
	}
	
	public State getNewState() {
		return newState;
	}

}