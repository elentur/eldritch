package oldVersion.elements;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oldVersion.enums.Actions;
import oldVersion.enums.Skills;
import oldVersion.exceptions.CardNotFoundException;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.Skill;
import oldVersion.gameItems.Stack;
import oldVersion.gameMechanics.Event;
import oldVersion.gameMechanics.EventExecuter;
import oldVersion.gameMechanics.IO;
import oldVersion.gameMechanics.TextAppearsTransition;
import oldVersion.gameMechanics.TokenAppearsTransition;
import oldVersion.gui.Animations;
import oldVersion.gui.Effects;
import oldVersion.gui.MenueTextures;
import oldVersion.gui.StageControll;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import oldVersion.listener.ActionsListListener;
import oldVersion.listener.SanityHealthListener;

import static oldVersion.enums.Events.*;

public class Investigator extends Card {
	private static final long serialVersionUID = 1L;
	private  int MAX_HEALTH;
	private  int MAX_SANITY;

	
	
	private IntegerProperty sanity = new SimpleIntegerProperty();
	private IntegerProperty health = new SimpleIntegerProperty();
	private Stack<ClueToken> clues;
	private IntegerProperty trainTickets = new SimpleIntegerProperty();
	private IntegerProperty shipTickets = new SimpleIntegerProperty();
	private Skill lore;
	private Skill influence;
	private Skill observation;
	private Skill strength;
	private Skill will;
	private Stack<Item> inventory;
	private Stack<Condition> condition;
	
	//private Image picture;
	private  Rectangle flatToken;
	private Map<String,String> names=null;
private int player;
	
	private ObservableList<Actions> actions;
	
	private boolean delayed = false;
	private String loadName;
	
	//Aktion und F�higkeit
	//Aktion f�r tot
	
	
	
	
	@SuppressWarnings("unchecked")
	public Investigator(String name){
		Class<?> clazz;
		this.loadName= name;
		try {
		
			clazz = Class.forName("oldVersion.investigators." + name);
			
			
		this.names=IO.readText(Global.language+"/" + name + ".txt");
	
		this.MAX_HEALTH=(int) clazz.getMethod("buildHealth").invoke(null);
		this.MAX_SANITY=(int) clazz.getMethod("buildSanity").invoke(null);
		this.name=(String) clazz.getMethod("buildName").invoke(null);
		this.sanity.set(this.MAX_SANITY);
		this.health.set(this.MAX_HEALTH);
		this.sanity.addListener(new SanityHealthListener<Number>(this.MAX_SANITY, this, true));

		this.health.addListener(new SanityHealthListener<Number>(this.MAX_HEALTH, this, false));
		
		this.lore=(Skill) clazz.getMethod("buildLore").invoke(null);
		this.influence=(Skill) clazz.getMethod("buildInfluence").invoke(null);
		this.observation=(Skill) clazz.getMethod("buildObservation").invoke(null);
		this.strength=(Skill) clazz.getMethod("buildStrength").invoke(null);
		this.will=(Skill) clazz.getMethod("buildWill").invoke(null);
		
		
		this.trainTickets.set((int) clazz.getMethod("buildTrainTicket").invoke(null));
		this.shipTickets.set((int) clazz.getMethod("buildShipTicket").invoke(null));
		this.clues= (Stack<ClueToken>) clazz.getMethod("buildClues").invoke(null);
		this.inventory=(Stack<Item>) clazz.getMethod("buildInventory").invoke(null);
		this.condition=new Stack<Condition>(false);
		this.actions=FXCollections.observableArrayList();//new ArrayList<Actions>();
		this.actions.addListener(new ActionsListListener<Actions>(this));
		//this.picture=(Image) clazz.getMethod("buildPicture").invoke(null);
		this.flatToken= new Rectangle(0,0,new ImagePattern(getPicture()));
		this.flatToken.widthProperty().bind(StageControll.getPrimaryStage().getScene().widthProperty().divide(12));
		this.flatToken.heightProperty().bind(StageControll.getPrimaryStage().getScene().widthProperty().divide(12));
		this.flatToken.setStrokeWidth(6);
		this.flatToken.setStrokeType(StrokeType.INSIDE);
		this.player=0;
		}catch(Exception e){
			
		}
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getOCCUPATION() {
		return names.get("occupation");
	}

	public String getQUOTE() {
		return names.get("quote");
	}

	public String getActionText() {
		return names.get("action");
	}

	public String getAblitiyText() {
		return names.get("ability");
	}

	public Image getPicture() {
		try {
		Class<?> textures=Class.forName("oldVersion.gui.MenueTextures");
		String name = loadName.replace(loadName.substring(0, 1),loadName.substring(0, 1).toLowerCase());
		Field f=textures.getField(name);
		
		return (Image)f.get(null);
		} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException | SecurityException e) {
			System.out.println("Fehler in Investigator.getPicture()");
		}
		return MenueTextures.akachiOnyele;
	}
	public Rectangle getFlatToken(){
		return flatToken;
	}

	public int getSanity() {
		return sanity.get();
	}

	public void setSanity(int sanity) {
		this.sanity.set(sanity);
	}
	
	public void looseSanity(int value){
		sanity.set( sanity.get() - value);
	}
	public void recoverSanity(int value){
		sanity.set( sanity.get() + value);
	}
	public IntegerProperty sanityProperty(){
		return sanity;
	}
	

	public int getHealth() {
		return health.get();
	}

	public void setHealth(int health) {
		this.health.set(health);
	}
	public void looseHealth(int value){
		health.set(health.get()-value);
	}
	public void recoverHealth(int value){
		health.set(health.get()+value);
	}
	public IntegerProperty healthProperty(){
		return health;
	}
	public boolean rest(){
		if(getSanity()==MAX_SANITY&&getHealth()==MAX_HEALTH)return false;
		if(actions.size()<2 && !actions.contains(Actions.rest)){
			EventExecuter.runEvent(
					Global.game, 
					this,
					new Event(recover,1,SANITY,
							new Event(recover,1,HEALTH)
							)
					);
			actions.add(Actions.rest);
			return true;
		}
		return false;
	}

	public Skill getLore() {
		return lore;
	}

	public void improveLore() {
		lore.improve();
	}

	public Skill getInfluence() {
		return influence;
	}

	public void improveInfluence() {
		influence.improve();
	}

	public Skill getObservation() {
		return observation;
	}

	public void improveObservation() {
		observation.improve();
	}

	public Skill getStrength() {
		return strength;
	}

	public void improveStrength() {
		strength.improve();
	}

	public Skill getWill() {
		return will;
	}

	public void improveWill() {
		will.improve();
	}
	
	public int getSkillValue(Skills skill){
		if(skill == Skills.influence)return getInfluence().getValue()+getInfluence().getImprovment();
		if(skill == Skills.lore)return getLore().getValue()+getLore().getImprovment();
		if(skill == Skills.observation)return getObservation().getValue()+getObservation().getImprovment();
		if(skill == Skills.strength)return getStrength().getValue()+getStrength().getImprovment();
		return getWill().getValue()+getWill().getImprovment();
	}

	public int getMAX_HEALTH() {
		return MAX_HEALTH;
	}

	public int getMAX_SANITY() {
		return MAX_SANITY;
	}

	public void setInventory(Stack<Item> inventory) {
		this.inventory = inventory;
	}


	public String getSTORY() {
		return names.get("story");
	}

	public Stack<ClueToken> getClues() {
		return clues;
	}

	public void addClues(ClueToken clue) {
		this.clues.add(clue);
	}
	public void removeClues(ClueToken clue) {
		try {
			this.clues.removeCard(clue);
		} catch (CardNotFoundException e) {
		}
		
	}
	public void removeClues() {
		try {
			this.clues.drawNextCard();
		} catch (CardNotFoundException e) {}
		
	}

	public int getTrainTickets() {
		return trainTickets.get();
	}
	public void removeTrainTicket(){
		if(trainTickets.get()>0)trainTickets.set(trainTickets.get()-1);
	}
	public boolean addTrainTickets() {
		if(this.trainTickets.get()+this.shipTickets.get()<2){
			if(actions.size()<2 && !actions.contains(Actions.prepare_for_Travel)){
				this.trainTickets.set(trainTickets.get()+1);
				actions.add(Actions.prepare_for_Travel);
				return true;
			}
			
			
		}
		return false;
	}

	public IntegerProperty trainTicketProperty(){
		return trainTickets;
	}
	public int getShipTickets() {
		return shipTickets.get();
	}
	public void removeShipTicket(){
		if(shipTickets.get()>0)shipTickets.set(shipTickets.get()-1);
	}

	public boolean addShipTickets() {
		if(this.trainTickets.get()+this.shipTickets.get()<2){
			if(actions.size()<2 && !actions.contains(Actions.prepare_for_Travel)){
				this.shipTickets.set(shipTickets.get()+1);
				actions.add(Actions.prepare_for_Travel);
				return true;
			}
			
			
		}
		return false;
	}
	public IntegerProperty shipTicketProperty(){
		return shipTickets;
	}

	public Stack<Condition> getCondition() {
		return condition;
	}

	public void addCondition(Condition card) {
		this.condition.add(card);
	}

	public List<Actions> getActions() {
		return actions;
	}

	public Stack<Item> getInventory() {
		return inventory;
	}

	public void addToInventory(Item card) {
		this.inventory.add(card);;
	}
	public boolean travel(){
		if(actions.size()<2 && !actions.contains(Actions.travel)){
			actions.add(Actions.travel);
			return true;
		}
		return false;
	}
	
	public boolean accquireAssets(){
		if(actions.size()<2 && !actions.contains(Actions.acquire_Assets)){
			actions.add(Actions.acquire_Assets);
			return true;
		}
		return false;
	}

	public boolean isDelayed() {
		return delayed;
	}

	public void setDelayed(boolean delayed) {
		this.delayed = delayed;
	}

	public void refreshNewRound() {
		actions.clear();
		
	}
	public boolean isBlessed(){
		///////Eigentliche überprüfung noch einfügen
		return false;
	}


}
