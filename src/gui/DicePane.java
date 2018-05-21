package gui;

import gamemechanics.Encounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import preparation.Preparation;

class DicePane extends AnchorPane {
    private final Encounter encounter;
     DicePane(Encounter encounter){
        this.encounter= encounter;
         Preparation preparation= encounter.getPreparation();
         int dice = preparation.getNumberOfDice();
         FlowPane root = new FlowPane();
         root.setAlignment(Pos.CENTER);
         //root.setPadding(new Insets(20));
         root.setVgap(50);
         root.setHgap(50);
         root.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
         root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
         SubScene msaa = createSubScene(
                 new PerspectiveCamera(),root);
         for(int i = 0; i< dice;i++){
             DiceGui diceGui = new DiceGui();


            root.getChildren().add(diceGui);
         }
         msaa.setEffect(Effects.dropShadow);
         this.getChildren().add(msaa);

    }


    private static SubScene createSubScene( PerspectiveCamera camera, Pane root) {

camera.setFieldOfView(1);
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        PointLight light2 = new PointLight(Color.color(0.6, 0.3, 0.4));
        light2.setTranslateX(400);
        light2.setTranslateY(0);
        light2.setTranslateZ(-400);


        AmbientLight ambientLight = new AmbientLight(Color.color(0.2, 0.2, 0.2));

        root.getChildren().addAll( ambientLight, light, light2);

        SubScene subScene = new SubScene(root, 500, 400, true,
                 SceneAntialiasing.BALANCED );
       // subScene.setFill(Color.BLUE);
        subScene.setCamera(camera);

        return subScene;
    }
}
