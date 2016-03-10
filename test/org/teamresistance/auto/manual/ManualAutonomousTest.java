package org.teamresistance.auto.manual;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.teamresistance.rules.StubHLReporting;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StubState;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(HierarchicalContextRunner.class)
public class ManualAutonomousTest {

    @ClassRule public static final StubHLReporting resource = new StubHLReporting();

    private List<State> stateList;
    private ManualAutonomous.Listener listener;
    private ManualAutonomous manualAutonomous;
    private AtomicReference<String> currentState;

    @Before
    public void setUp() {
        StubState a = new StubState("A", () -> currentState.set("A"));
        StubState b = new StubState("B", () -> currentState.set("B"));
        stateList = Arrays.asList(a, b);

        listener = Mockito.mock(ManualAutonomous.Listener.class);
        manualAutonomous = new ManualAutonomous(stateList, listener);
        currentState = new AtomicReference<>(null);
    }

    public class WhenButtonPressed {

        @Before
        public void setUp() {
            manualAutonomous.onButtonPress();
        }

        public class WhenHasNextState {

            private String nextState;

            @Before
            public void setUp() {
                nextState = stateList.get(0).getName();
            }

            @Test
            public void shouldEnterNextState() {
                assertThat(currentState.get()).isEqualTo(nextState);
            }

            @Test
            public void shouldNotifyListener() {
                verify(listener).onStateEntered(nextState);
            }

        }

        public class WhenNoNextState {

            private String lastState;

            @Before
            public void setUp() {
                int numberStates = stateList.size();
                lastState = stateList.get(numberStates - 1).getName();

                // Advance to the last state (after the initial button press)
                for (int i = 1; i < numberStates; i++) {
                    manualAutonomous.onButtonPress();
                }

                // Attempt to advance one state further
                manualAutonomous.onButtonPress();
            }

            @Test
            public void shouldRemainOnLastState() {
                assertThat(currentState.get()).isEqualTo(lastState);
            }

            @Test
            public void shouldNotifyListenerOnlyOnce() {
                verify(listener).onFinished();
            }

        }

    }

}