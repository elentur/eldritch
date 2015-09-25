
package gui;

import javafx.application.Platform;
import javafx.util.Duration;

public class Dialog {

	
	public static boolean okDialog(String text){
		
		DialogGui diag = new DialogGui(true);
		diag.setText(text);
		return false;
	}
}
