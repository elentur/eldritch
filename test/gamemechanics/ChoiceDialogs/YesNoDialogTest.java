package gamemechanics.ChoiceDialogs;


import Service.GameService;
import gamemechanics.choice.YesNoChoice;
import gui.Fonts;
import gui.InterfaceLinking;
import gui.choice.ButtonChoice;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class YesNoDialogTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Fonts.init(primaryStage);
        GameService game = GameService.getInstance();
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e -> primaryStage.close());
       // InterfaceLinking.init(primaryStage);
        primaryStage.show();
        YesNoChoice choice = new YesNoChoice("Test", "test text", null, null);
        game.addChoice(choice);

        System.out.println("test");


    }


}
