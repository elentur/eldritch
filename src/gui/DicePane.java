package gui;

import container.Die;
import container.Result;
import gamemechanics.Encounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import preparation.Preparation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DicePane extends HBox {
    private final Encounter encounter;
    private double width;
    private double height;
    private Map<DiceGui,Die> diceGuis;

    DicePane(Encounter encounter, double width, double height) {
        this.encounter = encounter;
        this.width = width;
        this.height = height;
        Preparation preparation = encounter.getPreparation();
        int dice = preparation.getNumberOfDice();
        Group root = new Group();
        SubScene dicesScene = createSubScene(
                new PerspectiveCamera(), root);
        double gap = DiceGui.cubeWidth;
        double x = DiceGui.cubeWidth * 1.8;
        double y = DiceGui.cubeWidth * 1.8;
        diceGuis = new HashMap<>();
        for (int i = 0; i < dice; i++) {
            DiceGui diceGui = new DiceGui();
            diceGui.setTranslateX(gap + (i % 6) * x);
            diceGui.setTranslateY(gap + (i / 6) * y);
            diceGuis.put(diceGui,null);
        }
        root.getChildren().addAll(diceGuis.keySet());
        dicesScene.setEffect(Effects.dropShadow);
        RollDiceButton diceButton = new RollDiceButton();
        diceButton.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                Result result = encounter.check();
                int i= 0;
                for (DiceGui diceGui : diceGuis.keySet()) {

                    Die die = result.get(i);
                    diceGuis.put(diceGui,die);
                    diceGui.rollDie(die);
                    diceGui.rerollButton.setOnMouseClicked(a -> {
                        result.rerollDie(die);
                        diceGui.rollDie(die);
                    });
                    diceGui.shiftButton.setOnMouseClicked(a -> {
                        result.shiftDie(die);
                        diceGui.shiftDie(die);
                    });
                    i++;
                }
                diceButton.setEnabled(false);
            }
        });
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().addAll(dicesScene, diceButton);
        this.getStyleClass().add("small-show-case");
    //    this.setBorder(new Border(new BorderStroke(Fonts.DARK, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.THIN)));

    }

    public void refresh(){
        if(encounter.getResult()!=null) {
            for (DiceGui diceGui : diceGuis.keySet()) {
                diceGui.rerollButton.setVisible(encounter.getResult().getReroll() > 0);
                diceGui.shiftButton.setVisible(diceGuis.get(diceGui).isShiftable());
            }
        }
    }

    private SubScene createSubScene(PerspectiveCamera camera, Group root) {
        camera.setFieldOfView(1);
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        PointLight light2 = new PointLight(Color.WHITE);
        light2.setTranslateX(400);
        light2.setTranslateY(0);
        light2.setTranslateZ(-400);
        AmbientLight ambientLight = new AmbientLight(Color.color(0.2, 0.2, 0.2));
        root.getChildren().addAll(ambientLight, light, light2);
        SubScene subScene = new SubScene(root, width, height, true,
                SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        //subScene.setFill(Color.BLUE);
        return subScene;
    }
}
