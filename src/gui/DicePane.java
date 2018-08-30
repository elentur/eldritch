package gui;

import container.Die;
import container.Result;
import enums.TestType;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import gui.buttons.InfoTextButton;
import gui.buttons.RoundButton;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.HashMap;
import java.util.Map;

public class DicePane extends HBox {
    private final Encounter encounter;
    private double width;
    private double height;
    private Map<DiceGui, Die> diceGuis;
    private SubScene dicesScene;
    RoundButton diceButton;
    InfoTextButton acceptButton;
    private BooleanProperty rolleIsDone = new SimpleBooleanProperty(false);

    public DicePane(Encounter encounter, double width, double height) {
        this.encounter = encounter;
        this.width = width;
        this.height = height;
        this.setMinHeight(height);
        this.setMaxHeight(height);
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setPadding(new Insets(0,20,0,20));
        Preparation preparation = encounter.getPreparation();
        acceptButton = new InfoTextButton("${accept_Button}");
        if(preparation.getTestTyp().equals(TestType.NONE)) {
            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            acceptButton.setStyleProperty(Fonts.getFont(0.25, Fonts.GREEN , Fonts.FontTyp.NORMAL));
            acceptButton.setInfoText( ResourceUtil.get("${success}", "ui"));

            encounter.check();
            this.getChildren().addAll(region,acceptButton);
        }else {
            int dice = preparation.getNumberOfDice();

            Group root = new Group();
            dicesScene = createSubScene(
                    new PerspectiveCamera(), root);
            double gap = DiceGui.cubeWidth;
            double x = DiceGui.cubeWidth * 1.8;
            double y = DiceGui.cubeWidth * 1.8;
            diceGuis = new HashMap<>();
            for (int i = 0; i < dice; i++) {
                DiceGui diceGui = new DiceGui();
                diceGui.setTranslateX(gap + (i % 6) * x);
                diceGui.setTranslateY(gap + (i / 6) * y);
                diceGuis.put(diceGui, null);
            }
            root.getChildren().addAll(diceGuis.keySet());
            dicesScene.setEffect(Effects.dropShadow);
            VBox diceButtonContainer = new VBox();
            diceButtonContainer.setAlignment(Pos.TOP_CENTER);
            diceButton = new RoundButton("${dice_Button}");
            diceButton.setOnMouseClicked(e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    Result result = encounter.check();
                    int i = 0;
                    for (DiceGui diceGui : diceGuis.keySet()) {

                        Die die = result.get(i);
                        diceGuis.put(diceGui, die);
                        diceGui.rollDie(die);
                        diceGui.rolledProperty().addListener(a -> diceRollHandler());
                        diceGui.rerollButton.setOnMouseClicked(a -> {

                            result.rerollDie(die);
                            refresh();
                            diceGui.rollDie(die);

                        });
                        diceGui.shiftButton.setOnMouseClicked(a -> {

                            result.shiftDie(die);
                            refresh();
                            diceGui.shiftDie(die);
                        });
                        i++;
                    }
                    this.getChildren().remove(diceButtonContainer);

                }
            });
            diceButtonContainer.getChildren().add(diceButton);
            HBox.setHgrow(diceButtonContainer, Priority.ALWAYS);
            this.getChildren().addAll(dicesScene, diceButtonContainer);
        }

        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(20);
        this.getStyleClass().add("small-show-case");

        // diceButtonContainer.setBorder(new Border(new BorderStroke(Fonts.RED, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.MEDIUM)));

    }

    private void diceRollHandler() {

        boolean allRolled = true;
        for (DiceGui dg : diceGuis.keySet()) {
            if (dg.rolledProperty().getValue() == false) {
                allRolled = false;
            }
        }
        if (allRolled) {
            rolleIsDone.setValue(true);
            if (!this.getChildren().contains(acceptButton)) {
                this.getChildren().add(acceptButton);
            }
            String damage = "";
            Color color = encounter.getResult().isSuccess() ? Fonts.GREEN : Fonts.RED;
            if (encounter instanceof CombatEncounter) {
                CombatEncounter ce = (CombatEncounter) encounter;
                damage += ": ";
                damage += encounter.getEncounterPart() == 2 ? ResourceUtil.get("${loose_x_health}", "ui", (ce.getActiveMonster().getDamage() - encounter.getResult().getNumberOfSuccess()) + "") :
                        ResourceUtil.get("${loose_x_sanity}", "ui", (ce.getActiveMonster().getHorror() - encounter.getResult().getNumberOfSuccess()) + "");
                acceptButton.setStyleProperty(Fonts.getFont(0.25, color, Fonts.FontTyp.NORMAL));
            } else {
                acceptButton.setStyleProperty(Fonts.getFont(0.25, color, Fonts.FontTyp.NORMAL));
            }
            acceptButton.setInfoText(encounter.getResult().isSuccess() ? ResourceUtil.get("${success}", "ui") : ResourceUtil.get("${fail}", "ui") + damage);

        }

    }

    public void setDiceSceneVisible(boolean visible) {
        dicesScene.setVisible(visible);
    }

    public void refresh() {
        if (encounter.getResult() != null) {
            for (DiceGui diceGui : diceGuis.keySet()) {
                diceGui.rerollButton.setVisible(encounter.getResult().getReroll() > 0);
                diceGui.shiftButton.setVisible(diceGuis.get(diceGui).isShiftable());
            }


        }


    }

    private SubScene createSubScene(PerspectiveCamera camera, Group root) {
        camera.setFieldOfView(1);
        PointLight light = new PointLight(Color.GRAY);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        PointLight light2 = new PointLight(Color.GRAY);
        light2.setTranslateX(400);
        light2.setTranslateY(0);
        light2.setTranslateZ(-400);
        AmbientLight ambientLight = new AmbientLight(Color.color(0.2, 0.2, 0.2));
        root.getChildren().addAll(ambientLight, light, light2);
        SubScene subScene = new SubScene(root, width*0.60, height, true,
                SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        //subScene.setFill(Color.BLUE);
        return subScene;
    }


    public InfoTextButton getAcceptButton() {
        return acceptButton;
    }

    public BooleanProperty getRolleIsDoneProperty() {
        return rolleIsDone;
    }
}
