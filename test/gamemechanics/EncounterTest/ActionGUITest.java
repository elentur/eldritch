package gamemechanics.EncounterTest;

import Service.GameService;
import enums.ConditionType;
import enums.TestType;
import gamemechanics.Action;
import gui.Fonts;
import gui.InterfaceLinking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;
import model.effects.GainCondition;
import model.effects.NullEffect;

public class ActionGUITest extends Application {

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
        scene.setOnKeyPressed(e -> {
            primaryStage.close();
            System.exit(0);
        });
        scene.getStylesheets().add("css/rootStyle.css");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        InterfaceLinking.init(primaryStage);
        primaryStage.show();
        primaryStage.setMaximized(true);
        Action a = new Action(inv,
                "actiontest",
                new GainCondition(ConditionType.DETAINED, inv),
                new NullEffect(),
                new NullEffect(),
                TestType.NONE,
                0
        );
        GameService.getInstance().addEncounter(a);
        Action b = new Action(inv,
                "actiontest",
                new NullEffect(),
                new GainCondition(ConditionType.DETAINED, inv),
                new NullEffect(),
                TestType.WILL,
                0
        );
        GameService.getInstance().addEncounter(b);
    }


}
