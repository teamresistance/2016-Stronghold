package org.teamresistance.auto.manual;

import com.sun.istack.internal.Nullable;

import org.teamresistance.util.joystick.Button;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import java.util.List;

/* TODO rename to something more generic and general-purpose */
public class ManualAutonomous extends State implements Button.Listener {

    private final StateMachine autoMachine;
    private final List<State> stateSequence;
    private final Listener listener;

    private int currentIndex;

    public ManualAutonomous(List<State> stateSequence, @Nullable Listener listener) {
        this.autoMachine = new StateMachine();
        this.stateSequence = stateSequence;
        this.listener = listener;

        stateSequence.forEach(autoMachine::addState);
    }

    @Override
    public void onEntry(StateTransition e) {
        // Idle
    }

    @Override
    public void onButtonPress() {
        if (currentIndex < stateSequence.size()) {
            String nextState = stateSequence.get(currentIndex).getName();
            notifyStateEntered(nextState);
            autoMachine.setState(nextState);
        }

        if (currentIndex == stateSequence.size()) {
            notifyFinished();
        }

        currentIndex++;
    }

    private void notifyStateEntered(String newState) {
        if (listener != null) {
            listener.onStateEntered(newState);
        }
    }

    private void notifyFinished() {
        if (listener != null) {
            listener.onFinished();
        }
    }

    public interface Listener {

        void onStateEntered(String newState);

        void onFinished();

    }

}
