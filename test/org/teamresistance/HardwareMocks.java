package org.teamresistance;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.wpi.first.wpilibj.Solenoid;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doAnswer;

public class HardwareMocks {

    public static void setUp(Solenoid solenoid) {
        AtomicBoolean solenoidEnabled = new AtomicBoolean(true);
        doAnswer(i -> solenoidEnabled.get()).when(solenoid).get();
        doAnswer(i -> {
            boolean newState = i.getArgumentAt(0, boolean.class);
            solenoidEnabled.set(newState);
            return null;
        }).when(solenoid).set(anyBoolean());
    }

}
