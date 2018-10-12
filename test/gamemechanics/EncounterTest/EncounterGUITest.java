package gamemechanics.EncounterTest;

import Service.GameService;
import container.ItemContainer;
import enums.FieldID;
import enums.FieldType;
import factory.ItemFactory;
import gamemechanics.choice.EncounterChoice;
import gui.Fonts;
import gui.InterfaceLinking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Field;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;

public class EncounterGUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Investigator inv = new AgnesBaker();
        Fonts.init(primaryStage);
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
       // InterfaceLinking.init(primaryStage);
        primaryStage.show();
        primaryStage.setMaximized(true);
    }




}
