package org.teamresistance.rules;

import org.junit.rules.ExternalResource;

import edu.wpi.first.wpilibj.HLUsageReporting;

public class StubHLReporting extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        // Use a dummy implementation to avoid a BaseSystemNotInitializedException
        HLUsageReporting.SetImplementation(new HLUsageReporting.Interface() {
            @Override
            public void reportScheduler() {}

            @Override
            public void reportPIDController(int num) {}

            @Override
            public void reportSmartDashboard() {}
        });
    }
}
