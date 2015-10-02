package gameMechanics;

import gameItems.Field;
import gui.FieldGui;
import javafx.animation.Transition;

public class TokenAppearsTransition {
	private Field field;
	private Transition transition;
	private FieldGui fieldGui;
	
	public TokenAppearsTransition(Transition transition, Field field, FieldGui fieldGui){
		this.field= field;
		this.transition=transition;
		this.fieldGui=fieldGui;
	}

	public FieldGui getFieldGui() {
		return fieldGui;
	}

	public Field getField() {
		return field;
	}

	public Transition getTransition() {
		return transition;
	}
}
