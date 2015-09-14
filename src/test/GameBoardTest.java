package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import elements.Investigator;
import elements.Monster;
import enums.FieldTyps;
import enums.Path;
import enums.Skills;
import enums.Space;
import gameItems.Field;
import gameItems.GameBoard;
import gameItems.Skill;
import gameMechanics.Event;

public class GameBoardTest {
	Field fieldA=new Field("a",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldB=new Field("b",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldC=new Field("c",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldD=new Field("d",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldE=new Field("e",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldF=new Field("f",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldG=new Field("g",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldH=new Field("h",Space.city,new Point(0,0),FieldTyps.City);
	Field fieldI=new Field("i",Space.city,new Point(0,0),FieldTyps.City);
	Investigator investigator1= new Investigator("AkachiOnyele");
	Investigator investigator2= new Investigator("CharlieKane");
	Investigator investigator3= new Investigator("CharlieKane");
	Monster monster1 = new Monster("Kultist", 2, 2, 1, 0, -1, new Event(),"Cthulhu");
	GameBoard gameBoard;
	Map<Field,Space> fields= new HashMap<Field,Space>();
	@Before
	public void setUp() throws Exception {
		fieldA.addNeighbour(fieldB, Path.ship);
		fieldA.addNeighbour(fieldC, Path.ship);
		fieldB.addNeighbour(fieldA, Path.ship);
		fieldB.addNeighbour(fieldC, Path.ship);
		fieldB.addNeighbour(fieldE, Path.ship);
		fieldC.addNeighbour(fieldA, Path.ship);
		fieldC.addNeighbour(fieldB, Path.ship);
		fieldC.addNeighbour(fieldD, Path.ship);
		fieldC.addNeighbour(fieldE, Path.ship);
		fieldD.addNeighbour(fieldC, Path.ship);
		fieldD.addNeighbour(fieldH, Path.ship);
		fieldE.addNeighbour(fieldC, Path.ship);
		fieldE.addNeighbour(fieldB, Path.ship);
		fieldE.addNeighbour(fieldF, Path.ship);
		fieldE.addNeighbour(fieldG, Path.ship);
		fieldF.addNeighbour(fieldE, Path.ship);
		fieldF.addNeighbour(fieldH, Path.ship);
		fieldF.addNeighbour(fieldG, Path.ship);
		fieldG.addNeighbour(fieldE, Path.ship);
		fieldG.addNeighbour(fieldF, Path.ship);
		fieldG.addNeighbour(fieldI, Path.ship);
		fieldH.addNeighbour(fieldD, Path.ship);
		fieldH.addNeighbour(fieldF, Path.ship);
		fieldH.addNeighbour(fieldI, Path.ship);
		fieldI.addNeighbour(fieldG, Path.ship);
		fieldI.addNeighbour(fieldH, Path.ship);
	
		fields.put(fieldA, fieldA.getSpace());
		fields.put(fieldB, fieldB.getSpace());
		fields.put(fieldC, fieldC.getSpace());
		fields.put(fieldD, fieldD.getSpace());
		fields.put(fieldE, fieldE.getSpace());
		fields.put(fieldF, fieldF.getSpace());
		fields.put(fieldG, fieldG.getSpace());
		fields.put(fieldH, fieldH.getSpace());
		fields.put(fieldI, fieldI.getSpace());
		fieldC.addInvestigator(investigator1);
		fieldF.addInvestigator(investigator2);
		fieldI.addInvestigator(investigator3);
		fieldD.addMonster(monster1);
		gameBoard = new GameBoard(fields);
	}
	@Test
	public void testGetInvestigatorField() {
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldC);
		assertTrue(gameBoard.getInvestigatorField(investigator2) ==fieldF);
		assertTrue(gameBoard.getInvestigatorField(investigator3) ==fieldI);
		assertFalse(gameBoard.getInvestigatorField(investigator1) ==fieldF);
	}

	@Test
	public void testGetMonsterField() {
		assertTrue(gameBoard.getMonsterField(monster1)==fieldD);
		assertFalse(gameBoard.getMonsterField(monster1)==fieldA);
	}

	@Test
	public void testMoveInvestigator() {
		
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldC);
		gameBoard.moveInvestigator(investigator1, fieldB);
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldB);
		assertFalse(gameBoard.getInvestigatorField(investigator1) ==fieldC);
		gameBoard.moveInvestigator(investigator1, fieldA);
		assertFalse(gameBoard.getInvestigatorField(investigator1) ==fieldA);
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldB);
	}
	@Test
	public void testMoveInvestigatorWithTickets() {
		investigator1.addShipTickets();
		investigator1.addTrainTickets();
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldC);
		gameBoard.moveInvestigator(investigator1, fieldB);
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldB);
		assertFalse(gameBoard.getInvestigatorField(investigator1) ==fieldC);
		gameBoard.moveInvestigator(investigator1, fieldA);
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldA);
		assertFalse(gameBoard.getInvestigatorField(investigator1) ==fieldB);
		gameBoard.moveInvestigator(investigator1, fieldC);
		assertTrue(gameBoard.getInvestigatorField(investigator1) ==fieldA);
		assertFalse(gameBoard.getInvestigatorField(investigator1) ==fieldC);
	}

	@Test
	public void testMoveMonster() {
		gameBoard.moveMonster(monster1, fieldA);
		assertFalse(gameBoard.getMonsterField(monster1)==fieldD);
		assertTrue(gameBoard.getMonsterField(monster1)==fieldA);
	}

	@Test
	public void testNextInvestigatorField() {
		System.out.println("N�chster Ermittler von Feld A aus: " + gameBoard.nextInvestigatorField(fieldA));
		System.out.println("N�chster Ermittler von Feld B aus: " + gameBoard.nextInvestigatorField(fieldB));
		System.out.println("N�chster Ermittler von Feld C aus: " + gameBoard.nextInvestigatorField(fieldC));
		System.out.println("N�chster Ermittler von Feld D aus: " + gameBoard.nextInvestigatorField(fieldD));
		System.out.println("N�chster Ermittler von Feld E aus: " + gameBoard.nextInvestigatorField(fieldE));
		System.out.println("N�chster Ermittler von Feld F aus: " + gameBoard.nextInvestigatorField(fieldF));
		System.out.println("N�chster Ermittler von Feld G aus: " + gameBoard.nextInvestigatorField(fieldG));
		System.out.println("N�chster Ermittler von Feld H aus: " + gameBoard.nextInvestigatorField(fieldH));
		System.out.println("N�chster Ermittler von Feld I aus: " + gameBoard.nextInvestigatorField(fieldI));
		
	}

	@Test
	public void testShortestWaysFieldField() {
		System.out.println(gameBoard.shortestWays(fieldA, fieldF));
	}

}
