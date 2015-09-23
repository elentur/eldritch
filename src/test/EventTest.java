package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import elements.Gate;
import elements.Investigator;
import enums.Omen;
import enums.Skills;
import exceptions.CardNotFoundException;
import gameBuild.Game;
import gameBuild.Global;
import gameItems.AncientOne;
import gameItems.Skill;
import gameMechanics.Event;
import gameMechanics.EventExecuter;

import static enums.Events.*;
import static enums.Skills.*;

public class EventTest {
	Game game;
	Investigator investigator1 = new Investigator("CharlieKane");
	Investigator investigator2 = new Investigator("AkachiOnyele");
	@Before
	public void setUp() throws Exception {
		Global.language="src/language/english";
		List<Investigator> investigators = new ArrayList<Investigator>();
		investigators.add(investigator1);
		investigators.add(investigator2);
		AncientOne ancientOne = new AncientOne("Cthulhu");
		game=new Game(ancientOne);
		game.setInvestigators(investigators);
		game.getGameBoard().getInvestigatorField(investigator2).setGate(game.getGateStack().drawNextCard());
	}

	@Test
	public void testGain() {
		//gain one random Spell
		Event spellEvent = new Event(gain,1,_random,SPELL);
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getInventory().getSize()==1);
		
		//gain three random spells
		spellEvent = new Event(gain,3,_random,SPELL);
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getInventory().getSize()==4);
		
		//gain one special spell
		String name="";
		try {
			name= game.getSpellDeck().showNextCard().getName();
			game.getSpellDeck().shuffle();
		} catch (CardNotFoundException e) {
			
		}
		spellEvent = new Event(gain,1,name,SPELL);
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getInventory().getSize()==5);
		assertTrue(investigator1.getInventory().contains(name));
		
		
		//gain one random spell and one special Spell

		name="";
		try {
			name= game.getSpellDeck().showNextCard().getName();
			game.getSpellDeck().shuffle();
		} catch (CardNotFoundException e) {
			
		}
		spellEvent = new Event(gain,1,_random,SPELL, new Event(gain,1,name,SPELL));
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getInventory().getSize()==7);
	}
	
	@Test
	public void testImprove() {
		//improve lore
		Event spellEvent = new Event(improve,lore);
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getLore().getImprovment()==1);
		
		//improve lore and strength

		spellEvent = new Event(improve,lore,new Event(improve, strength));
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getLore().getImprovment()==2);
		assertTrue(investigator1.getStrength().getImprovment()==1);
		
		//improve will and gain one random spell
		spellEvent = new Event(improve,will, new Event(gain,1,_random,SPELL));
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getWill().getImprovment()==1);
		assertTrue(investigator1.getInventory().getSize()==1);
	}
	
	@Test
	public void testRecoverLoose() {
		//loose Sanity
		Event spellEvent = new Event(loose,2,SANITY);
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getMAX_SANITY()-2==investigator1.getSanity());
		//recover Sanity 
		spellEvent = new Event(recover,5,SANITY);
		EventExecuter.runEvent(game, investigator1, spellEvent);
		assertTrue(investigator1.getMAX_SANITY()==investigator1.getSanity());

	}
	
	@Test
	public void testCloseGate() {
		//gate is Open
		assertTrue(game.getGameBoard().getInvestigatorField(investigator2).getGate()!=null);
		assertTrue(game.getGateStack().getSizeDropStack()==0);
		Event spellEvent = new Event(close,_this);
		EventExecuter.runEvent(game, investigator2, spellEvent);
		//gate is closed
		assertTrue(game.getGameBoard().getInvestigatorField(investigator2).getGate()==null);
		assertTrue(game.getGateStack().getSizeDropStack()==1);
	}
	

}
