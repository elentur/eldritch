package gameMechanics;

import java.util.List;

import elements.Card;
import elements.ClueToken;
import elements.Condition;
import elements.Investigator;
import elements.Item;
import elements.Token;
import enums.Events;
import enums.Skills;
import exceptions.CardNotFoundException;
import gameBuild.Game;
import gameItems.Field;

import static enums.Events.*;
import static enums.Skills.*;

public class EventExecuter {
	private static List<Object> events;
	private static Game game;
	private static Investigator investigator;
	
	public static void  runEvent(Game game1, Investigator aktivInvestigator, Event event ){
		try{
			game=game1;
			investigator=aktivInvestigator;
			events = event.getEvents();
			
			while(!events.isEmpty()){
				Events eventName = (Events) events.remove(0);
				if(eventName == gain){
					gainEvent();
				}else if(eventName== spend){
					spendEvent();
				}else if(eventName== improve){
					improveEvent();
				}else if(eventName== discard){
					discardEvent();
				}else if(eventName== move){
					moveEvent();
				}else if(eventName== choose){
					chooseEvent();
				}else if(eventName== loose){
					looseEvent();
				}else if(eventName== spawn){
					spawnEvent();
				}else if(eventName== recover){
					recoverEvent();
				}else if(eventName== delay){
					delayEvent();
				}else if(eventName== retreat){
					retreatEvent();
				}else if(eventName== advance){
					advanceEvent();
				}else if(eventName== close){
					closeEvent();
				}else if(eventName== resolve){
					resolveEvent();
				}else if(eventName== roll){
					rollEvent();
				}else if(eventName== solve){
					solveEvent();
				}
				if(!events.isEmpty()){
					Event nextEvent= (Event) events.get(0);
					events= nextEvent.getEvents();
				}
				
			}
			
		}catch(Exception e){
			System.out.println("Exception in Hauptroutine");
		}
	
	}
	private static void solveEvent() {
		// TODO Auto-generated method stub
		
	}
	private static void rollEvent() {
		// TODO Auto-generated method stub
		
	}
	private static void resolveEvent() {
		// TODO Auto-generated method stub
		
	}
	private static void closeEvent() {
		Events gate = (Events) events.remove(0);
		Field field;
		if(gate==_this){
			field =game.getGameBoard().getInvestigatorField(investigator);
			game.getGateStack().dropCard(field.removeGate());
		}
		
	}
	private static void advanceEvent() {
		Events object = (Events) events.remove(0);
		int number = (int)events.remove(0);
		if (object == DOOM){
			for(int i = 0; i<number;i++){
				game.getDoomTracker().advanceDoom();
			}
		}else if(object == OMEN){
			for(int i = 0; i<number;i++){
				game.getOmenTracker().advanceOmen();
			}
		}
		
	}
	private static void retreatEvent() {
		Events object = (Events) events.remove(0);
		int number = (int)events.remove(0);
		if (object == DOOM){
			for(int i = 0; i<number;i++){
				game.getDoomTracker().retrieveDoom();
			}
		}else if(object == OMEN){
			for(int i = 0; i<number;i++){
				game.getOmenTracker().retrieveOmen();
			}
		}
		
	}
	private static void delayEvent() {
		investigator.setDelayed(true);
		
	}
	private static void recoverEvent() {
		int number = (int) events.remove(0);
		Events sanityHealth = (Events) events.remove(0);
		if (sanityHealth == HEALTH){
			investigator.recoverHealth(number);
		}else if (sanityHealth == SANITY){
			investigator.recoverSanity(number);
		}	
	}
	private static void looseEvent() {
		int number = (int) events.remove(0);
		Events sanityHealth = (Events) events.remove(0);
		if (sanityHealth == HEALTH){
			investigator.looseHealth(number);
		}else if (sanityHealth == SANITY){
			investigator.looseSanity(number);
		}
	}
	private static void spawnEvent() {
		// TODO Auto-generated method stub
		
	}
	
	private static void chooseEvent() {
		// TODO Auto-generated method stub
		
	}
	private static void moveEvent() {
		// TODO Auto-generated method stub
		
	}
	private static void discardEvent() {
		// TODO Auto-generated method stub
		
	}
	private static void spendEvent() {
		int number = (int)events.remove(0);
		if(investigator.getClues().getSize()>=number){
			try {
				for(int i = 0; i < number; i++){
					game.getCluePool().dropCard(investigator.getClues().drawNextCard());
				}
			} catch (CardNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private static void improveEvent() {
		Skills skill = (Skills) events.remove(0);
		if(skill == lore)investigator.getLore().improve();
		if(skill == influence)investigator.getInfluence().improve();
		if(skill == observation)investigator.getObservation().improve();
		if(skill == strength)investigator.getStrength().improve();
		if(skill == will)investigator.getWill().improve();
		
	}
	private static void gainEvent() {
		try{
			int number=1;
			boolean random =false;
			String name="";
			Object object=null;
			Item card=null;
			Token token=null;
			while(!events.isEmpty()){
				object = events.remove(0);
				if(object instanceof Integer){
					number = (int) object;
				}else if(object instanceof String){
					name =(String) object;
				}else if(object instanceof Events){
					for(int i = 0; i< number;i++){
						if((Events)object == _random){
							random=true;
						}else if ((Events)object == SPELL){
							if (random){
								card = game.getSpellDeck().drawNextCard();
							}else{
								card =  game.getSpellDeck().drawCard(name);
							}
							investigator.getInventory().add(card);
						}else if ((Events)object == ASSET){
							if (!events.isEmpty())object = events.remove(0);
							if(object instanceof Events && (Events)object == RESERVE){
								//Ziehen vom ReserveDeck
							}else if (random){
								card = game.getAssetDeck().drawNextCard();
							}else{
								card =  game.getAssetDeck().drawCard(name);
							}
							investigator.getInventory().add(card);
						}else if ((Events)object == ARTIFACT){
							if (random){
								card = game.getArtifactDeck().drawNextCard();
							}else{
								card =  game.getArtifactDeck().drawCard(name);
							}
							investigator.getInventory().add(card);
						}else if ((Events)object == CONDITION){
							if (random){
								card = game.getConditionDeck().drawNextCard();
							}else{
								card =  game.getConditionDeck().drawCard(name);
							}
							investigator.addCondition((Condition)card);
						}else if ((Events)object == CLUE){
							if (random){
								token = game.getCluePool().drawNextCard();
							}else{
								token =  game.getCluePool().drawCard(name);
							}
							investigator.addClues((ClueToken)token);
						}
					}
					
				}
					
			}
			if(object instanceof Event)events.add(object);
			//System.out.println(investigator.getInventory() + "  " + investigator.getClues());
			
		}catch(Exception e){
			System.out.println("Exception");
		}
	}
}
