package org.teamresistance.auto.defense;

/**
 * A minimal {@link Defense} implementation that allows the Autonomous state
 * to be tested without the time delay and moving parts involved in crossing
 * an actual defense.
 */
public class DummyDefense extends Defense {

    @Override
    public boolean isReversed() {
        return true;
    }

    @Override
    public void whileCrossing() {
        // Signal that we have "crossed" the defense.
        this.setCrossed();
    }

}
