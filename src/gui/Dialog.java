
package gui;

import java.util.Map;

import gameBuild.Global;
import gameMechanics.IO;
import gameMechanics.TextAppearsTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Dialog {
	private static Map<String,String> names;
	
	public static boolean infoDialog(String text){
		
//		DialogGui diag = new DialogGui(true);
//		diag.setText(text);
		names = IO.readText(Global.language+"/GameScreen.txt");
		Global.textAppearsTransitionList.add(new TextAppearsTransition(names.get(text), Effects.fontColor));
		
		return false;
	}
}
