package org.teamresistance.util.state;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
	 * Registers a new state of the specified type.
	 * @param stateType the type
	 * @return <code>true</code> if the new instance was successfully added
	 */
	public boolean addState(Class<? extends State> stateType) {
		if(stateType == null) {
			return false;
		}
		return addState(stateType, stateType.getSimpleName());
	}
	
	/**
	 * Registers a new state of the specified type, and associates it with the specified name.
	 * If the name is a null pointer, then the state's runtime class name is used
	 * @param stateType the type
	 * @param stateName the name
	 * @return <code>true</code> if the new instance was successfully added
	 */
	public boolean addState(Class<? extends State> stateType, String stateName) {
		if (stateType == null) {
			return false;
		}
		if (stateName == null) {
			stateName = stateType.getSimpleName();
		} 
		if (containsState(stateName)) {
			return false;
		}
		State instance = newInstance(stateType);
		
		if (instance == null) {
			return false;
		}
		instance.init();
		states.put(stateName, instance);
		return true;
	}
	
	/**
	 * Exits the current state, and enters the state of the specified name.
	 * If the state is the current state, Then this method returns without altering this StateMachine.
	 * @param stateName the name
	 * @return <code>true</code> if and only if the state of this machine was changed
	 */
	public boolean setState(String stateName) {
		if (stateName == null) {
			return false;
		}
		return transition(states.get(stateName));
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
		newState.onEntry(transition);
		
		currentState = newState;
		return true;
	}
	
	/**
	 * Returns a new instance of the specified State subclass,
	 * or null if a new instance cannot be created.
	 * @param stateType the type
	 * @return the instance
	 */
	private State newInstance(Class<? extends State> stateType) {
		try {
			Constructor<? extends State> ctor = stateType.getDeclaredConstructor(StateMachine.class);
			ctor.setAccessible(true);
			return ctor.newInstance(this);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}
