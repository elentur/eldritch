package gamemechanics.EncounterTest;

import gui.Dialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CombatEncounterGUITest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        VBox root = new VBox();
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        primaryStage.show();
        Dialog dlg = new Dialog("No Scene created.");
        dlg.showAndWait();
    }
}
