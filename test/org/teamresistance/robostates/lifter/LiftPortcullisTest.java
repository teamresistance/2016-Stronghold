package org.teamresistance.robostates.lifter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StubReturnState;
import org.teamresistance.util.state.StubState;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LiftPortcullisTest {

	@Mock private Solenoid lifterSolenoid;
	@Mock private DigitalInput bottomSwitch;

	private StateMachine stateMachine;
	private LiftPortcullis liftPortcullis;

	@Before
	public void setUp() {
		// Set up the behavior of the mock Solenoid's "get" and "set"
		AtomicBoolean solenoidEnabled = new AtomicBoolean(true);
		doAnswer(i -> solenoidEnabled.get()).when(lifterSolenoid).get();
		doAnswer(i -> {
			boolean newState = i.getArgumentAt(0, boolean.class);
			solenoidEnabled.set(newState);
			return null;
		}).when(lifterSolenoid).set(anyBoolean());

		// Initialize with mocks. This isolates the class being tested; we now know the code
		// in LiftPortcullis is solely responsible for any test cases failing.
		liftPortcullis = new LiftPortcullis(lifterSolenoid, bottomSwitch);

		// Add the State under test and the mocked peripheral States (to allow gotoState)
		stateMachine = new StateMachine();
		stateMachine.addState(liftPortcullis);
		stateMachine.addState(new StubReturnState(), "MoveLifterDown");
		stateMachine.addState(new StubState(), "RaiseFlipper");
	}

	@Test
	public void whenStateEntered_shouldRetractSolenoid() {
		// No preconditions or assumptions...

		// Trigger the state's onEntry
		stateMachine.setState("LiftPortcullis");

		// Solenoid should now be retracted
		assertThat(lifterSolenoid.get()).isFalse();
	}

	@Test
	public void whenLimitSwitchNotHit_shouldGoToMoveLifterDown() {
		// Assume we haven't hit the limit switch
		when(bottomSwitch.get()).thenReturn(false);

		// Trigger the state's onEntry
		stateMachine.setState("LiftPortcullis");

		// We should now be entering the next state
		assertThat(stateMachine.getCurrentState()).isEqualTo("MoveLifterDown");
	}

	@Test
	public void whenLimitSwitchHit_shouldGoToRaiseFlipper() {
		// Assume we've hit the limit switch
		when(bottomSwitch.get()).thenReturn(true);

		// Trigger the state's onEntry
		stateMachine.setState("LiftPortcullis");

		// We should now be entering the next state
		assertThat(stateMachine.getCurrentState()).isEqualTo("RaiseFlipper");
	}
}