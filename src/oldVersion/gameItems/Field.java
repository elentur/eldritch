package oldVersion.gameItems;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oldVersion.elements.ClueToken;
import oldVersion.elements.Gate;
import oldVersion.elements.Investigator;
import oldVersion.elements.Monster;
import oldVersion.elements.Mystery;
import oldVersion.elements.Mythos;
import oldVersion.enums.FieldTyps;
import oldVersion.enums.Path;
import oldVersion.enums.Space;
import oldVersion.gameBuild.Global;
import oldVersion.gameMechanics.IO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class Field implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<Field,Path> neighbours;
	private Space space;
	private String name;
	private Point position;
	private ObservableList<Investigator> investigators;
	private List<Investigator> deadInvestigators;
	private ObservableList<Monster> monsters;
	private final ObjectProperty<ClueToken> clue;
	private final ObjectProperty<Gate> gate;
	private Mystery mystery;
	private Mythos roumor;
	private FieldTyps fieldTyp;
	private Map<String,String> names;

	
	public Field(String name,Space space, Point position,FieldTyps typ ){
		 names=IO.readText(Global.language+"/fields.txt");
		this.neighbours = new HashMap<Field,Path>();
		this.space=space;
		this.name=name;
		this.position=position;
		this.fieldTyp=typ;
		this.investigators= FXCollections.observableArrayList();
		this.monsters = FXCollections.observableArrayList();
		this.mystery=null;
		this.roumor=null;
		clue=new SimpleObjectProperty<ClueToken>(this, "clue", null);
		gate=new SimpleObjectProperty<Gate>(this, "gate", null);

	}
	

	public FieldTyps getFieldTyp() {
		return fieldTyp;
	}
	public void addNeighbour(Field field, Path path){
		neighbours.put(field, path);
	}
	public List<Investigator> getInvestigators() {
		return investigators;
	}
	public ObservableList<Investigator> getObservalbleInvestigators() {
		return investigators;
	}
	
	public void addInvestigator(Investigator investigator){
		investigators.add(investigator);
	}
	
	public boolean removeInvestigator(Investigator investigator){
		return investigators.remove(investigator);
	}
	public boolean containsInvestigator(Investigator investigator){
		return investigators.contains(investigator);
	}
	public List<Investigator> getDeadInvestigators() {
		return deadInvestigators;
	}
	public void addDeadInvestigator(Investigator investigator){
		deadInvestigators.add(investigator);
	}
	public boolean containsDeadInvestigator(Investigator investigator){
		return deadInvestigators.contains(investigator);
	}
	public ObservableList<Monster> getObservalbleMonsters() {
		return monsters;
	}
	
	public List<Monster> getMonsters() {
		return monsters;
	}
	public void addMonster(Monster monster){
		monsters.add(monster);
	}
	
	public boolean removeMonster(Monster monster){
		return monsters.remove(monster);
	}
	public boolean containsMonster(Monster monster){
		return monsters.contains(monster);
	}
	
	public ObjectProperty<ClueToken> clueProperty() { 
		return clue; 
	}
	

	public ClueToken getClue() {
		return clue.get();
	}

	public void setClue(ClueToken clue) {
		this.clue.set(clue);
	}
	public ClueToken removeClue() {
		ClueToken c= clue.get();
		clue.set(null);
		return c;
	}

	public ObjectProperty<Gate> gateProperty() { 
		return gate; 
	}
	
	public Gate getGate() {
		return gate.get();
	}
	public Gate removeGate() {
		Gate removedGate =gate.get();
		gate.set(null);
		return removedGate;
	}

	public void setGate(Gate gate) {
		this.gate.set(gate);
	}

	public Mystery getMystery() {
		return mystery;
	}

	public void setMystery(Mystery mystery) {
		this.mystery = mystery;
	}

	public Mythos getRoumor() {
		return roumor;
	}

	public void setRoumor(Mythos roumor) {
		this.roumor = roumor;
	}

	public Map<Field, Path> getNeighbours() {
		return neighbours;
	}

	public Space getSpace() {
		return space;
	}

	public String getName() {
		return names.get(name);
	}
	public String getNameNote(){
		return names.get(name+"Note");
	}
	public Point getPosition() {
		return position;
	}
	
	public String toString(){
		return name;
	}




	
}
