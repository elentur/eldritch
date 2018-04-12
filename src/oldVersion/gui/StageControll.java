package oldVersion.gui;

import javafx.stage.Stage;

public class StageControll {
	private static Stage primaryStage=null;
	
	public static void setPrimaryStage(Stage stageControll){
		primaryStage=stageControll;
	}
	public static Stage getPrimaryStage(){
		//Animations.blendingDown(primaryStage.getScene());
		return primaryStage;
	}
}
