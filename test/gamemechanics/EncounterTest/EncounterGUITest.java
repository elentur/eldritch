package gamemechanics.EncounterTest;

import Service.GameService;
import enums.FieldType;
import gamemechanics.choice.EncounterChoice;
import gui.Fonts;
import gui.InterfaceLinking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Field;

public class EncounterGUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Fonts.init(primaryStage);
        Field  f = new Field(FieldType.CITY,0);
        GameService.getInstance().setField(f);

        EncounterChoice encounterChoice = new EncounterChoice( f);
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e->{
            primaryStage.close();
            System.exit(0);
        });
        scene.getStylesheets().add("css/rootStyle.css");
        primaryStage.setOnCloseRequest(e->   System.exit(0));
        InterfaceLinking.init(primaryStage);
        primaryStage.show();
        primaryStage.setMaximized(true);
        GameService.getInstance().addChoice(encounterChoice);
    }




}
