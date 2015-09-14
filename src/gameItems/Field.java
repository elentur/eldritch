package gameItems;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import elements.Gate;
import elements.Investigator;
import elements.Monster;
import elements.Mystery;
import elements.Mythos;
import enums.FieldTyps;
import enums.Path;
import enums.Space;
import gameBuild.Global;
import gameMechanics.IO;

public class Field implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Field,Path> neighbours;
	private Space space;
	private String name;
	private Point position;
	private List<Investigator> investigators;
	private List<Investigator> deadInvestigators;
	private List<Monster> monsters;
	private Boolean clue;
	private Gate gate;
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
		this.investigators=new ArrayList<Investigator>();
		this.monsters = new ArrayList<Monster>();
		this.clue=false;
		this.gate=null;
		this.mystery=null;
		this.roumor=null;
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
	

	public Boolean getClue() {
		return clue;
	}

	public void setClue(Boolean clue) {
		this.clue = clue;
	}

	public Gate getGate() {
		return gate;
	}
	public Gate removeGate() {
		Gate removedGate =gate;
		gate=null;
		return removedGate;
	}

	public void setGate(Gate gate) {
		this.gate = gate;
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
