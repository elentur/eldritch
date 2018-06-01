package gamemechanics.ChoiceDialogs;


import Service.GameService;
import gamemechanics.choice.Choice;
import gamemechanics.choice.YesNoChoice;
import gui.DialogGui;
import gui.Fonts;
import gui.choice.YesNoDialog;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oldVersion.gameBuild.Game;

public class YesNoDialogTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Fonts.init(primaryStage);

        GameService game =  GameService.getInstance();
        game.getChoiceProperty().addListener(e->startChoiceDialog(game.getChoiceProperty().getValue()));
        VBox root = new VBox();
        Scene scene = new Scene(root);
        scene.setFill(Color.RED);
        primaryStage.setScene(scene);
        scene.setOnKeyPressed(e->primaryStage.close());
        primaryStage.show();
        YesNoChoice choice= new YesNoChoice("Test", "test text",null,null);
        game.addChoice(choice);


    }

    private void startChoiceDialog(Choice choice) {
        if(choice==null){
            return;
        }
        if(choice instanceof YesNoChoice){
            DialogGui dlg = new YesNoDialog((YesNoChoice) choice);
            dlg.showAndWait();
        }

    }


}
