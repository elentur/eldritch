package gameMechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enums.Encounters;
import enums.Skills;

public  class DiceBonus {
	
	
	private List<BoniValue> bonus;
	private Encounters encounter;
	private boolean oncePerRound;
	private boolean used;
	private boolean onDiscard;
	
	public DiceBonus(int bonus,Skills skill, Encounters encounter){
		this(bonus,skill,encounter,false,false);
	}
	public DiceBonus(int bonus,Skills skill, Encounters encounter, boolean oncePerRound){
		this(bonus,skill,encounter,oncePerRound,false);
	}
	public DiceBonus(int bonus,Skills skill, Encounters encounter, boolean oncePerRound,boolean onDiscard){
		this(encounter,oncePerRound,onDiscard,new BoniValue(bonus, skill));
	}
	public DiceBonus( Encounters encounter, boolean oncePerRound,boolean onDiscard,BoniValue...boniValues){

		this.bonus=new ArrayList<BoniValue>(Arrays.asList(boniValues));
		this.encounter=encounter;
		this.oncePerRound=oncePerRound;
		this.used=false;
		this.onDiscard=onDiscard;
	}
	
	public boolean isOnDiscard() {
		return onDiscard;
	}

	public boolean isOncePerRound() {
		return oncePerRound;
	}
	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public List<BoniValue> getBonus() {
		return bonus;
	}

	public Encounters getEncounter() {
		return encounter;
	}
	public int getValueForSkill(Skills skill){
		for(BoniValue bv: getBonus()){
			if(bv.getSkill()==skill){
				return bv.getValue();
			}
		}
		return 0;
	}
}
