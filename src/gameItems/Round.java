package gameItems;

import java.io.Serializable;

import enums.Phases;

public class Round implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int numberOfRounds;
	private int numberOfInvestigators;
	private int activeInvestigator;
	private int leadInvestigator;
	private Phases phase;
	
	
	public Round(int numberOfInvestigators){
		this.numberOfInvestigators=numberOfInvestigators;
		activeInvestigator=0;
		phase = Phases.action;
		numberOfRounds=1;
		leadInvestigator=1;
	}
	
	public int getNumberOfInvestigators() {
		return numberOfInvestigators;
	}
	public void DecNumberOfInvestigators() {
		this.numberOfInvestigators--;
	}
	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	public int getActiveInvestigator() {
		return ((activeInvestigator+(leadInvestigator-1))% numberOfInvestigators)+1;
	}
	public int getLeadInvestigator() {
		return leadInvestigator;
	}
	public void setLeadInvestigator(int leadInvestigator) {
		this.leadInvestigator=leadInvestigator;
	}
	public Phases getPhase() {
		return phase;
	}
	public void next(){
		activeInvestigator++;
		if(getActiveInvestigator()==1){
			if(phase==Phases.mythos){
				phase= Phases.action;
				numberOfRounds++;
			}else if(phase==Phases.action){
				phase= Phases.encounter;
			}else if(phase==Phases.encounter){
				phase= Phases.mythos;
			}
			
		}
		
	}
	
	
}
