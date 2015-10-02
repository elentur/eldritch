package gameItems;

import java.io.Serializable;
import java.util.Map;

import elements.ClueToken;
import elements.Gate;
import elements.Investigator;
import enums.Phases;
import gameBuild.Global;
import gameMechanics.IO;
import gameMechanics.TextAppearsTransition;
import gui.Effects;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Round implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int numberOfRounds;
	private int numberOfInvestigators;
	private IntegerProperty activeInvestigator;
	private IntegerProperty investigatorCount;
	private int leadInvestigator;
	private final ObjectProperty<Phases> phase;
	private Map<String,String> names;
	
	
	
	public Round(int numberOfInvestigators){
		names = IO.readText(Global.language+"/GameScreen.txt");
		this.numberOfInvestigators=numberOfInvestigators;
		activeInvestigator = new SimpleIntegerProperty(0);
		investigatorCount= new SimpleIntegerProperty();
		investigatorCount.addListener(a->{
			activeInvestigator.set(((investigatorCount.get()+(leadInvestigator-1))% numberOfInvestigators));
		});
		phase=new SimpleObjectProperty<Phases>(this, "phase", Phases.action);
		numberOfRounds=1;
		leadInvestigator=1;
		//investigatorCount.set(-1);
		if (!Global.debug){
			Global.game.spawnGates(getGateNumber());
			Global.game.spawnClues(getClueNumber());
		}
		//next();
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
		
		if(phase.get()==Phases.mythos){
			phase.set(Phases.action);
			Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get("action"), Effects.fontColorRed));
			for(Investigator inv :Global.game.getInvestigators()){
				inv.refreshNewRound();
			}
			numberOfRounds++;
			return;
		}
		investigatorCount.set(investigatorCount.get()+1);
		if(getActiveInvestigator()==0){
			 if(phase.get()==Phases.action){
				phase.set(Phases.encounter);
				Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get("encounter"), Effects.fontColorBlue));
			}else if(phase.get()==Phases.encounter){
				phase.set(Phases.mythos);
				Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get("mythos"), Effects.fontColorGreen));
			}
		}
		Thread textTrans = new Thread(new Runnable() {
			@Override
			public void run() {
					Platform.runLater(new Runnable(){

						@Override
						public void run() {
								
							 TextAppearsTransition tat = new TextAppearsTransition(Global.game.getActiveInvestigator().getName()+names.get("turn"), Effects.fontColor);
							 tat.setGraphicNode(new Rectangle(
									 Global.game.getActiveInvestigator().getPicture().getWidth()/2,
									 Global.game.getActiveInvestigator().getPicture().getHeight()/2,
									 new ImagePattern(Global.game.getActiveInvestigator().getPicture())));
							 Global.textAppearsTransitionList.add(tat);
						}
						});
					}
			});
			textTrans.setDaemon(true);
			textTrans.start();
		
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
