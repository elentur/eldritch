package gameItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import elements.Artifact;
import elements.Asset;
import elements.Investigator;
import elements.Item;
import enums.Skills;
import gameBuild.Global;
import gameMechanics.BoniValue;
import gameMechanics.DiceBonus;
import gameMechanics.IO;
import gameMechanics.TextAppearsTransition;
import gui.Effects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Dice implements Serializable {
	private static final long serialVersionUID = 1L;
	private Skills skill;
	private Investigator investigator;
	private int number=0;
	private ArrayList<Integer> allDice;
	private Random rnd;
	private Map<String,String> names=null;
	private IntegerProperty succsess;
	
	
	
	public Dice(Skills skill,Investigator investigator){
		this.names=IO.readText(Global.language+"/GameScreen.txt");
		this.skill= skill;
		this.rnd = new Random();
		this.allDice = new ArrayList<Integer>();
		this.succsess=new SimpleIntegerProperty(0);
		this.investigator=investigator;
		number=investigator.getSkillValue(skill);
		getDice();
		
	}

	private void getDice() {
		DiceBonus diceBonus = null;
		if(!investigator.getInventory().isEmpty()){
			for (Item item : investigator.getInventory().getStack()) {
				if (item instanceof Asset || item instanceof Artifact) {
					DiceBonus db = ((Asset) item).getDiceBonus();
					if (db != null) {
	
						if (diceBonus == null || diceBonus.getValueForSkill(skill) < db.getValueForSkill(skill)) {
							diceBonus = db;
						}
					}
				}
			}
			if (diceBonus !=null){
				number+= diceBonus.getValueForSkill(skill);
			}
		}
	}
	public ArrayList<Integer> roll(){
		
		allDice.clear();
		for(int i= 0 ; i < number;i++){
			if(Global.debug)allDice.add(6);
			else allDice.add(rnd.nextInt(6)+1);
			
			
		}
		allDice.sort(Collections.reverseOrder());
		refreshSuccsess();
		return allDice;
	}

	private void refreshSuccsess() {
		int i =0;
		for(Integer result : allDice){
			if (result ==6 || result == 5 ||(result == 4 && investigator.isBlessed())){
				i++;
			}
		}
		succsess.set(i);
		
	}

	public int reroll(int i) {
		if(!investigator.getClues().isEmpty()){
			investigator.removeClues();
			allDice.set(i, rnd.nextInt(6)+1);

			Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get("rerollDice")+ allDice.get(i), Effects.fontColor));
			
		}
		refreshSuccsess();
		return allDice.get(i);
	}

	public  IntegerProperty successProperty() {
		return this.succsess;
	}
	

	public  int getSuccess() {
		return this.successProperty().get();
	}

	public int getNumber() {
		return number;
	}
	
	
	
	
	
}
