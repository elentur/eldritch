package gui;



import java.awt.Toolkit;

import enums.Screens;
import gameBuild.Global;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class startGame extends Application {

	@Override
	public void start(Stage primaryStage) {
		Global.language="src/language/english";
		StageControll.setPrimaryStage(primaryStage);
		MenueTextures.load();
		GameTextures.load();
		Scene scene = new Scene(new Group(),960,540);
		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		primaryStage.fullScreenProperty().addListener(a->{
			Effects.font = Font.loadFont("file:src/fonts/UGLYQUA.TTF", primaryStage.getWidth()/60);
			});
		
		primaryStage.show();
		Animations.blendingDown(Screens.StartScreen);
		
		
		
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}
