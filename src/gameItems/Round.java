package gameItems;

import java.io.Serializable;

import elements.ClueToken;
import elements.Gate;
import elements.Investigator;
import enums.Phases;
import gameBuild.Global;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Round implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int numberOfRounds;
	private int numberOfInvestigators;
	private IntegerProperty activeInvestigator;
	private IntegerProperty investigatorCount;
	private int leadInvestigator;
	private final ObjectProperty<Phases> phase;
	
	
	
	public Round(int numberOfInvestigators){
		this.numberOfInvestigators=numberOfInvestigators;
		activeInvestigator = new SimpleIntegerProperty(0);
		investigatorCount= new SimpleIntegerProperty(0);
		investigatorCount.addListener(a->{
			activeInvestigator.set(((investigatorCount.get()+(leadInvestigator-1))% numberOfInvestigators));
		});
		phase=new SimpleObjectProperty<Phases>(this, "phase", Phases.action);
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
		return activeInvestigator.get();
	}
	public int getLeadInvestigator() {
		return leadInvestigator;
	}
	public IntegerProperty activInvestigatorProperty(){
		return activeInvestigator;
	}
	public void setLeadInvestigator(int leadInvestigator) {
		this.leadInvestigator=leadInvestigator;
	}
	public Phases getPhase() {
		return phase.get();
	}
	
	public ObjectProperty<Phases> phaseProperty(){
		return phase;
	}
	public void next(){
		investigatorCount.set(investigatorCount.get()+1);;
		if(getActiveInvestigator()==0){
			if(phase.get()==Phases.mythos){
				phase.set(Phases.action);
				for(Investigator inv :Global.game.getInvestigators()){
					inv.refreshNewRound();
				}
				numberOfRounds++;
			}else if(phase.get()==Phases.action){
				phase.set(Phases.encounter);
			}else if(phase.get()==Phases.encounter){
				phase.set(Phases.mythos);
			}
			
		}
		
	}
	public int getGateNumber(){
		if(numberOfInvestigators<5)return 1;
		return 2;
	}
	public int getClueNumber(){
		if(numberOfInvestigators<3)return 1;
		if(numberOfInvestigators<5)return 2;
		if(numberOfInvestigators<7)return 3;
		return 4;
	}
	public int getMonsterNumber(){
		if(numberOfInvestigators<3)return 1;
		if(numberOfInvestigators<7)return 2;
		return 3;
	}
	
	
}
