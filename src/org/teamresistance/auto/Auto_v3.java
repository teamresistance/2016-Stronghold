package org.teamresistance.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.teamresistance.IO;
import org.teamresistance.auto.defense.*;
import org.teamresistance.util.Time;
import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

/**
 * Created by 1234v on 2/23/16.
 */
public class Auto_v3 extends State {

    // These are all variables
    //For rotation and options Variables
//    public double defensePositionOne = -0.6;
//    public double defensePositionTwo = -0.4;
//    public double defensePositionThree = -0.1;
//    public double defensePositionFour = 0.1;
    public double defensePositionFive = 0.4;

    //Option Variables
    public double defensePosition = defensePositionFive;
    public double towerPosition = -defensePosition;

    /*
    * Key for defense Type:
    * 0 - Cheval
    * 1 - Rough Terrain
    * 2 - Drawbridge
    * 3 - Moat
    * 4 - Portcullis
    * 5 - Ramparts
    * 6 - RockWall
     */
    public int defenseType = 1;

    // Time Variables
    public double currentTime;
    public double moveToDefenseTime = 1.5;
    public double crossDefenseTime = 3;
    public double moveToTowerTime = 2;
    public double rotateToTargetTime = 2;

    //Power Variables
    public double moveToDefensePower = 0.5;
    public static double crossDefensePower = 0.7;
    public double moveToTowerPower = 0.5;
    public double stablePower = 0.0;

    //Rotation Variables
    //Note to Self, 1 to -1 Range, Clockwise is positive
    public double stableRotation = 0.0;
    public double rotateToTowerRotation = defensePosition;
    public double rotateToTargetRotation = towerPosition;

    //For StateMachines
    State autoModes;
    enum State {moveToDefense, crossDefense, moveToTower,rotateToTarget, idle}

    //Note to Self IO.turnTo(IDK)
    public void onEntry(StateTransition e) {

        //autoModes = new StateMachine();

        autoModes = State.moveToDefense;

        switch (autoModes) {
            case moveToDefense:
                if (currentTime <= moveToDefenseTime) {
                    //Drive up to defense
                    SmartDashboard.putBoolean("moveToDefense Finished", true);
                    IO.robotDrive.arcadeDrive(moveToDefensePower, stableRotation);
                 }
                break;
            case crossDefense:
                if (currentTime <= crossDefenseTime) {
                    //Cross defense
                    Defense[] defenseTypeArray = new Defense[]{
                            new DefenseCheval(), new DefenseRoughTerrain(), new DefenseDrawbridge(), new DefenseMoat(),
                            new DefensePortcullis(), new DefenseRamparts(), new DefenseRockWall()
                    };
                    SmartDashboard.putBoolean("crossDefense Finished", true);
                    defenseTypeArray[defenseType].beginCrossing();
                }
                break;
            case moveToTower:
                if (currentTime <= moveToTowerTime) {
                    //Move to Tower
                    SmartDashboard.putBoolean("moveToTower Finished", true);
                    IO.robotDrive.arcadeDrive(moveToTowerPower, rotateToTowerRotation);
                }
                break;
            case rotateToTarget:
                if (currentTime <= rotateToTargetTime) {
                    //Move to Target
                    SmartDashboard.putBoolean("rotateToTarget Finished", true);
                    IO.robotDrive.arcadeDrive(stablePower, rotateToTargetRotation);
                }
                break;
            case idle:
                //Sit Still
                SmartDashboard.putBoolean("Stable Mode", true);
                IO.robotDrive.arcadeDrive(stablePower, stableRotation);
                break;
        }

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
