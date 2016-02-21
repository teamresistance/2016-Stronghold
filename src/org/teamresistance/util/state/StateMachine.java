package org.teamresistance.util.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Finite State Machine (FSM). The FSM can be in one single state at a time. 
 * Users should instantiate the machine, provide it with states and (case-sensitive) names,
 * set it to an initial state, and then initialize the machine.
 * 
 * @author Mathis
 */
public class StateMachine {
	/**
	 * This FSM's current state. Can never be null after initialization
	 */
	private State currentState;
	
	private Map<String, State> states;
	
	public StateMachine() {
		currentState = null;
		states = new HashMap<>();
	}
	
	/**
	 * Updates this State Machine's current state.
	 */
	public void update() {
		if(currentState != null) {
			currentState.update();
		}
	}

	/**
	 * Registers the specified state.
	 * @param state the state
	 * @return <code>true</code> if the new instance was successfully added
	 */
	public boolean addState(State state) {
		return state != null && addState(state, state.getClass().getSimpleName());
	}
	
	/**
	 * Registers the specified state instance, and associates it with the specified name.
	 * If the name is a null pointer, then the state's runtime class name is used
	 * @param state the state
	 * @param stateName the name
	 * @return <code>true</code> if the new instance was successfully added
	 */
	public boolean addState(State state, String stateName) {
		if (state == null) {
			return false;
		}
		if (stateName == null) {
			stateName = state.getClass().getSimpleName();
		} 
		if (containsState(stateName)) {
			return false;
		}
		
		state.setStateMachine(this);
		state.setName(stateName);

		states.put(stateName, state);
		return true;
	}
	
	/**
	 * Exits the current state, and enters the state of the specified name.
	 * If the state is the current state, Then this method returns without altering this StateMachine.
	 * @param stateName the name
	 * @return <code>true</code> if and only if the state of this machine was changed
	 */
	public boolean setState(String stateName) {
		return stateName != null && transition(states.get(stateName));
	}
	
	public State getState(String stateName) {
		if(stateName == null) {
			return null;
		}
		if(!containsState(stateName)) {
			return null;
		}
		return states.get(stateName);
	}

	public int getNumStates() {
		return states.size();
	}
	public boolean containsState(String stateName) {
		return states.containsKey(stateName);
	}
	
	/**
	 * Attempts to transition into the specified state.
	 * @param newState the state
	 * @return <code>true</code> if and only if the transition was successful.
	 */
	private boolean transition(State newState) {
		if (newState == null || newState == currentState) {
			return false;
		}
		StateTransition transition = new StateTransition(currentState, newState);
		if (currentState != null) {
			currentState.onExit(transition);
		}
		currentState = newState;
		
		newState.onEntry(transition);
		
		return true;
	}
	
	public String getCurrentState() {
		return currentState.getName();
	}

}