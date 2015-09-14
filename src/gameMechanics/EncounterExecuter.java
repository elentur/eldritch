package gameMechanics;


import elements.Investigator;
import gameBuild.Game;

public class EncounterExecuter {
	private static Encounter encounter;
	private static Game game;
	private static Investigator investigator;
	
	public static void  runEvent(Game game1, 
			Investigator aktivInvestigator, Encounter encounter1 ){
		try{
			game=game1;
			investigator=aktivInvestigator;
			encounter = encounter1;
			showText();
			
		}catch(Exception e){}
	}
	private static void showText(){
		
	}
}
