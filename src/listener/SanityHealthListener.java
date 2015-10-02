package listener;

import elements.Investigator;
import gameBuild.Global;
import gameMechanics.TextAppearsTransition;
import gui.Effects;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class SanityHealthListener<T> implements ChangeListener<Number> {
private int max;
private  Investigator investigator;
private boolean sanity;
	public SanityHealthListener(int max,Investigator investigator,boolean sanity){
		this.max=max;
		this.investigator=investigator;
		this.sanity=sanity;
	}
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		String s= "";
		if(sanity){
			int i = newValue.intValue() - oldValue.intValue();
			if(i>0)s="+";
			//if(Global.game.getActiveInvestigator()==investigator)
				if(!(oldValue.intValue()>max || newValue.intValue()>max))
					Global.textAppearsTransitionList.add(new TextAppearsTransition(s+i, Effects.fontColorBlue,Effects.fontVeryBig));
		
			sanityChangeListener(observable,max);
		}else{
			int i = newValue.intValue() - oldValue.intValue();
			if(i>0)s="+";
		//	if(Global.game.getActiveInvestigator()==investigator)	
			if(!(oldValue.intValue()>max || newValue.intValue()>max))
				Global.textAppearsTransitionList.add(new TextAppearsTransition(s+i, Effects.fontColorRed,Effects.fontVeryBig));
		
			healthChangeListener(observable,max);
		}
		
	}
	
	private static void sanityChangeListener(Observable a, int maxSanity){
		IntegerProperty sanity = (SimpleIntegerProperty)a;
		if(sanity.get()<0)sanity.set(0);
		if(sanity.get()>maxSanity)sanity.set(maxSanity);
	}
	
	private static void healthChangeListener(Observable a, int maxHealth){
		IntegerProperty health = (SimpleIntegerProperty)a;
		if(health.get()<0)health.set(0);
		if(health.get()>maxHealth)health.set(maxHealth);
	}
}
