package org.teamresistance.robostates.lifter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.teamresistance.HardwareMocks;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StubState;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Time.class)
public class DriveThroughDrawbridgeTest {

    @Mock private RobotDrive robotDrive;
    @Mock private Solenoid flipperSolenoid;

    private StateMachine stateMachine;
    private DriveThroughDrawbridge driveThroughDrawbridge;

    @Before
    public void setUp() {
        // Initialize Mockito mocks manually since we're not using the MockitoRunner
        MockitoAnnotations.initMocks(this);

        // Mock the static Time class we can control the output of getTime()
        PowerMockito.mockStatic(Time.class);

        HardwareMocks.setUp(flipperSolenoid);
        driveThroughDrawbridge = new DriveThroughDrawbridge(robotDrive, flipperSolenoid);

        stateMachine = new StateMachine();
        stateMachine.addState(driveThroughDrawbridge, "DriveThroughDrawbridge");
        stateMachine.addState(new StubState(), "TeleopLifterIdle");
    }

    @Test
    public void whenStateEntered_ShouldDriveStraight() {
        stateMachine.setState("DriveThroughDrawbridge");

        // The robot should have been told to drive forward
        verify(robotDrive).arcadeDrive(anyDouble(), 0);
    }

    // TODO the rest of the tests

}