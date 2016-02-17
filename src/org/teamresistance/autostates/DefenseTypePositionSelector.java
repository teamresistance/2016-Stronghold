package org.teamresistance.auto;

import org.teamresistance.util.state.State;
import org.teamresistance.util.state.StateMachine;
import org.teamresistance.util.state.StateTransition;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefenseTypePositionSelector{
	int defensePos;
	int defenseType; 

	public DefenseTypePositionSelector() {
		SmartDashboard.putNumber("position", 3);
		SmartDashboard.putNumber("defense type", 5);
		setDefenseType();
		setDefensePos();
	}
	
	/*void crossDefense() {
		AutonomousDefenses crosser = new AutonomousDefenses();
		
		switch(defenseType) {
		//case 1: crosser.crossPortcullis();
		//	break;
		//case 2: crosser.crossChevalDeFrise();
		//	break;
		case 3: crosser.crossMoat();
			break;
		case 4: crosser.crossRamparts();
			break;
		//case 5: crosser.crossDrawbridge();
		//	break;
		//case 6: crosser.crossSallyPort();
		//	break;
		case 7: crosser.crossRockWall();
			break;
		default: crosser.crossRoughTerrain();
			break;

		}
		
	}*/
	
	void setDefenseType() {
		defenseType = (int) SmartDashboard.getNumber("defense type");
	}
	
	void setDefensePos() {
		defensePos = (int) SmartDashboard.getNumber("position");
	}
	
	public int getDefensePos() {
		return defensePos;
	}
	
	public int getDefenseType(){
		return defenseType;
	}
	/*
	public void getTargetDefense(Defenses crosser) {
		/*
		//get defense type
		defenseType = SmartDashboard.getNumber();
		switch(defenseType) {
		case 1: crosser.crossPortcullis();
			break;
		case 2: crosser.crossChevalDeFrise();
			break;
		case 3: crosser.crossMoat();
			break;
		case 4: crosser.crossRamparts();
			break;
		case 5: crosser.crossDrawbridge();
			break;
		case 6: crosser.crossSallyPort();
			break;
		case 7: crosser.crossRockWall();
			break;
		default: crosser.crossRoughTerrain();
			break;
		}
		
		//get the position of the defense being crossed
		defensePos = SmartDashboard.getNumber();
		*/
	/*	
		SendableChooser defenseChooser = new SendableChooser();
		defenseChooser.addDefault("Portcullis", crossPortcullis());
		defenseChooser.addObject("Cheval de Frise", crossChevalDeFrise());
		defenseChooser.addObject("Moat", crossMoat());
		defenseChooser.addObject("Ramparts", crossRamparts());
		defenseChooser.addObject("Drawbridge", crossDrawbridge());
		defenseChooser.addObject("Sally Port", crossSallyPort());
		defenseChooser.addObject("Rock Wall", crossRockWall());
		defenseChooser.addObject("Rough Terrain", crossRoughTerrain());
		SmartDashboard.putData("Choose defense", defenseChooser);*/
		
		// this uses a very, very ugly workaround and requires the TowerGoalChooser file; revise. 
		/*SendableChooser posChooser = new SendableChooser();
		posChooser.addDefault("Position 2", new PositionChooser(2));
		posChooser.addObject("Position 3", new PositionChooser(3));
		posChooser.addObject("Position 4", new PositionChooser(4));
		posChooser.addObject("Position 5", new PositionChooser(5));
		SmartDashboard.putData("Choose position", posChooser);
		int targetGoal = posChooser.getSelected().getValue();
		*/
}
