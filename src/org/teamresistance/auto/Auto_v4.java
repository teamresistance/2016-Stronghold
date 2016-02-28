package org.teamresistance.auto;


import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateTransition;

/**
 * Created by 1234v on 2/23/16.
 */
public class Auto_v4 extends State {
    static double currentTime = 0;

    @Override
    public void onEntry(StateTransition e) {

    }

    @Override
    public void update() {
        //Constantly get time
        currentTime += Time.getDelta();
    }

    @Override
    public void onExit(StateTransition e) {

    }
}
