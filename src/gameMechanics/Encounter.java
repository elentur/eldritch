package gameMechanics;

import java.io.Serializable;

import enums.Skills;

public class Encounter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Skills skill;
	int modificator;
	Event event;
	Encounter pass;
	Encounter fail;
	String text;
	
	public Encounter(String textPath, Skills skill, int modificator, Event event, Encounter pass, Encounter fail ){
		//TextLaden
		this.skill=skill;
		this.modificator=modificator;
		this.event=event;
		this.pass =pass;
		this.fail=fail;
		
	}
	public Encounter(String textPath,Skills skill, int modificator, Event event ){
		this(textPath,skill,modificator,event,null,null);
	}
	
	public String getText(){
		return text;
	}
	public Event getEvent(){
		return event;
	}
	public Skills getSkill(){
		return skill;
	}
	public int getModificator(){
		return modificator;
	}
	
	public Encounter getPass(){
		return pass;
	}
	public Encounter getFail(){
		return fail;
	}

}
