package listener;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class InvestigatorListener {

	public static void sanityChangeListener(Object a, int maxSanity){
		IntegerProperty sanity = (SimpleIntegerProperty)a;
		if(sanity.get()<0)sanity.set(0);
		if(sanity.get()>maxSanity)sanity.set(maxSanity);
	}
	
	public static void healthChangeListener(Object a, int maxHealth){
		IntegerProperty health = (SimpleIntegerProperty)a;
		if(health.get()<0)health.set(0);
		if(health.get()>maxHealth)health.set(maxHealth);
	}


}
