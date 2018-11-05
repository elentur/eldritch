package gui.buttons;

import Service.GameService;
import enums.FieldConnections;
import enums.PathType;
import enums.PhaseTypes;
import enums.SituationType;
import gamemechanics.Action;
import gamemechanics.choice.EncounterChoice;
import gamemechanics.choice.MonsterChoice;
import gui.Animations;
import gui.Effects;
import gui.Fonts;
import gui.InterfaceLinking;
import gui.interfaceelements.ContextWheel;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import lombok.Getter;
import lombok.Setter;
import model.Field;
import model.Item.Investigator;
import model.Item.Monster;
import model.effects.Move;

import java.util.List;

public class FieldButton extends Group {

    private final static Image city = new Image("images/gameBoard/CitySpace.png", 120, 120, true, true, false);
    private final static Image sea = new Image("images/gameBoard/SeaSpace.png", 120, 120, true, true, false);
    private final static Image wilderness = new Image("images/gameBoard/WildernessSpace.png", 120, 120, true, true, false);
    private final static Image gateImage = new Image("images/gameBoard/Gate.png", 100, 100, true, true, false);
    private final static Image expeditionImage = new Image("images/gameBoard/Expedition.png", 100, 100, true, true, false);
    private final static Image clueImage = new Image("images/gameBoard/Clue.png", 100, 100, true, true, false);
    private final static Image eldritchImage = new Image("images/gameBoard/Eldritch.png", 100, 100, true, true, false);
    private final static Image mysteryImage = new Image("images/gameBoard/Mystery.png", 100, 100, true, true, false);
    private final static Image rumorImage = new Image("images/gameBoard/Rumor.png", 100, 100, true, true, false);


    @Getter
    private final Field field;
    private final FlowPane investigators;
    private final FlowPane monsters;
    private final FlowPane gateExpedition;
    @Setter
    private ContextWheel wheel;
    private boolean mouseOver;
    private final ImageView gate;
    private final ImageView expedition;
    private final ImageView clue;
    private final Label clueLabel;
    private final ImageView eldritch;
    private final Label eldritchLabel;
    private final ImageView mystery;
    private final ImageView rumor;
    private final Group gateGroup;
    private final OmenButton omen;

    private final Circle colorOverlay;


    private final  Button button;


    private boolean isDragging;
    private boolean pathIsLegal;

    public FieldButton(Field field, double x, double y) {
        button = new Button(getBackgroundImage(field));
        colorOverlay = new Circle(60);
        colorOverlay.setVisible(false);
        colorOverlay.setTranslateX(72);
        colorOverlay.setTranslateY(75);
        colorOverlay.setMouseTransparent(true);
        colorOverlay.setBlendMode(BlendMode.SCREEN);
        this.getChildren().addAll(button, colorOverlay);
        this.field = field;
        this.setTranslateX(x);
        this.setTranslateY(y);

        gateExpedition = new FlowPane(5, 5);
        gateExpedition.setTranslateX(70);
        gateExpedition.setTranslateY(-200);
        gateExpedition.setOrientation(Orientation.HORIZONTAL);
        gateExpedition.setAlignment(Pos.CENTER);
        gateExpedition.setPrefWidth(405);
        this.getChildren().add(gateExpedition);

        investigators = new FlowPane(5, 5);
        investigators.setTranslateY(150);
        investigators.setTranslateX(-50);
        investigators.setOrientation(Orientation.HORIZONTAL);
        investigators.setAlignment(Pos.CENTER);
        investigators.setPrefWidth(255);
        this.getChildren().add(investigators);

        monsters = new FlowPane(5, 5);
        monsters.translateYProperty().bind(monsters.heightProperty().multiply(-1));
        monsters.setTranslateX(-50);
        monsters.setOrientation(Orientation.HORIZONTAL);
        monsters.setAlignment(Pos.CENTER);
        monsters.setPrefWidth(255);
        this.getChildren().add(monsters);


        gate = new ImageView(gateImage);
        gate.setFitHeight(200);
        gate.setFitWidth(200);
        gate.setEffect(Effects.dropShadow);
        omen = new OmenButton(new Image("images/interface/Blue_Stars.png", 100, 100, false, true, true));
        omen.setTranslateX(50);
        omen.setTranslateY(50);
        gateGroup = new Group(gate, omen);
        
        expedition = new ImageView(expeditionImage);
        expedition.setFitHeight(200);
        expedition.setFitWidth(200);
        expedition.setEffect(Effects.dropShadow);

        clue = new ImageView(clueImage);
        clue.setFitHeight(100);
        clue.setFitWidth(100);
        clue.setTranslateY(-40);
        clue.setTranslateX(120);
        clue.setEffect(Effects.dropShadow);
        clueLabel = new Label("");
        clueLabel.styleProperty().bind(Fonts.getFont(0.5, Fonts.WHITE, Fonts.FontTyp.BOLD));
        clueLabel.setTranslateY(-40);
        clueLabel.setTranslateX(120);
        clueLabel.setPrefWidth(100);
        clueLabel.setPrefHeight(100);
        clueLabel.setAlignment(Pos.CENTER);
        clueLabel.setTextAlignment(TextAlignment.CENTER);
        clueLabel.getStyleClass().add("text-stroke-black");


        eldritch = new ImageView(eldritchImage);
        eldritch.setFitHeight(100);
        eldritch.setFitWidth(100);
        eldritch.setTranslateY(70);
        eldritch.setTranslateX(-80);
        eldritch.setEffect(Effects.dropShadow);
        eldritchLabel = new Label("");
        eldritchLabel.styleProperty().bind(Fonts.getFont(0.5, Fonts.WHITE, Fonts.FontTyp.BOLD));
        eldritchLabel.setTranslateY(70);
        eldritchLabel.setTranslateX(-80);
        eldritchLabel.setPrefWidth(100);
        eldritchLabel.setPrefHeight(100);
        eldritchLabel.setAlignment(Pos.CENTER);
        eldritchLabel.setTextAlignment(TextAlignment.CENTER);
        eldritchLabel.getStyleClass().add("text-stroke-black");

        mystery = new ImageView(mysteryImage);
        mystery.setFitHeight(100);
        mystery.setFitWidth(100);
        mystery.setTranslateY(-40);
        mystery.setTranslateX(-80);
        mystery.setEffect(Effects.dropShadow);

        rumor = new ImageView(rumorImage);
        rumor.setFitHeight(100);
        rumor.setFitWidth(100);
        rumor.setTranslateY(70);
        rumor.setTranslateX(120);
        rumor.setEffect(Effects.dropShadow);

        //  this.setBorder(new Border(new BorderStroke(Fonts.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.MEDIUM)));
        field.getUpdate().addListener(e -> {
            if (field.getUpdate().getValue()) {
                update();

            }
        });


        button.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!isDragging && e.getButton().equals(MouseButton.PRIMARY)) {
              if(GameService.getInstance().isChooseFieldMode() && field.isChooseAble()){
                  GameService.getInstance().setChooseFieldMode(false);
                  for(Field f: GameService.getInstance().getGameBoard().getFields()){
                      f.setChooseAble(false);
                  }
                  GameService.getInstance().setChosenField(field);
                  GameService.getInstance().addEffect(GameService.getInstance().getChooseFieldEffect());
              }else if(!GameService.getInstance().isChooseFieldMode()){
                    standartClickEvents();
                }
            }
            isDragging = false;
        });
        button.addEventHandler(MouseEvent.DRAG_DETECTED, e -> isDragging = true);


        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            mouseOver = true;
            if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {
                Field fieldOfInv = GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getActiveInvestigator());
                if (fieldOfInv != null && !field.equals(fieldOfInv)) {
                    showPath();
                }
            }
            update();
        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            mouseOver = false;
            clearPath();
            update();
        });
        update();


    }

    private void standartClickEvents(){
        if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {

            if (wheel != null) {
                wheel.remove();
                return;
            }
            if (GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getActiveInvestigator()).equals(field) &&
                    wheel == null) {
                this.wheel = new ContextWheel(field.getFieldAction());
                this.getChildren().add(wheel);
            } else if (!GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getActiveInvestigator()).equals(field)) {
                if (pathIsLegal) {
                    Action move = new Action(GameService.getInstance().getEncounteringInvestigator(), "move", new Move(field.getFieldID(), GameService.getInstance().getEncounteringInvestigator()), SituationType.MOVE);
                    GameService.getInstance().addEncounter(move);
                }
            }
        } else if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ENCOUNTER) &&
                GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getActiveInvestigator()).equals(field)) {
            if (!field.getMonster().isEmpty()) {
                GameService.getInstance().addChoice(new MonsterChoice(field.getFieldID()));
            } else {
                GameService.getInstance().addChoice(new EncounterChoice(field));
            }
        }
    }

    private void showPath() {
        if(GameService.getInstance().isChooseFieldMode()) {
            return;
        }
        this.pathIsLegal = false;

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        if (inv.getDoneActions().contains(new Action(inv, "move", null, SituationType.MOVE))) {
            return;
        }
        List<Field> path = GameService.getInstance().getGameBoard().getPath(
                GameService.getInstance().getFieldOfInvestigator(inv),
                field,
                inv);
        if (path != null) {
            if (path.size() <= 3) {
                InterfaceLinking.gameBoardGUI.getMap().showPath(path);
                if (path.size() == 2) {
                    pathIsLegal = true;
                } else {
                    FieldConnections connection = FieldConnections.getConnection(path.get(1), path.get(2));
                    if ((connection.getPathType().equals(PathType.SHIP) && GameService.getInstance().getEncounteringInvestigator().getShipTicket() > 0) ||
                            (connection.getPathType().equals(PathType.TRAIN) && GameService.getInstance().getEncounteringInvestigator().getTrainTicket() > 0)) {
                        pathIsLegal = true;
                    }
                }

            }
        }
    }

    private void clearPath() {
        if(!GameService.getInstance().isChooseFieldMode()) {
            InterfaceLinking.gameBoardGUI.getMap().clearPath();
        }
    }

    private void update() {
        createGateExpedition();
        createTokens();
        createInvestigators();
        createMonster();
        showFieldMode();
        field.getUpdate().setValue(false);

    }

    private void showFieldMode() {
        if(GameService.getInstance().isChooseFieldMode() && field.isChooseAble()){
          colorOverlay.setFill(Color.GREEN);
          colorOverlay.setVisible(true);
        }else if(GameService.getInstance().isChooseFieldMode() && !field.isChooseAble()){
            colorOverlay.setFill(Color.RED);
            colorOverlay.setVisible(true);
        }else{
            colorOverlay.setVisible(false);
        }
    }

    private void createTokens() {
        if (field.getNumberOfClues() <= 0) {
            if (this.getChildren().contains(clue)) {
                Animations.removeEffect(clue, () -> this.getChildren().remove(clue));
            }

        } else if (field.getNumberOfClues() > 0 && !this.getChildren().contains(clue)) {
            this.getChildren().add(clue);
            Animations.spawnEffect(clue);
        }
        this.getChildren().remove(clueLabel);
        if (field.getNumberOfClues() > 1) {
            clueLabel.setText(field.getNumberOfClues() + "");
            this.getChildren().add(clueLabel);
        }
        if (field.getNumberOfEldritchTokens() <= 0) {
            if (this.getChildren().contains(eldritch)) {
                Animations.removeEffect(eldritch, () -> this.getChildren().remove(eldritch));
            }
        } else if (field.getNumberOfEldritchTokens() > 0 && !this.getChildren().contains(eldritch)) {
            this.getChildren().add(eldritch);

            Animations.spawnEffect(eldritch);
        }

        this.getChildren().remove(eldritchLabel);
        if (field.getNumberOfEldritchTokens() > 1) {
            eldritchLabel.setText(field.getNumberOfEldritchTokens() + "");
            this.getChildren().add(eldritchLabel);
        }
        if (!field.hasRumor()) {
            if (this.getChildren().contains(rumor)) {
                Animations.removeEffect(rumor, () -> this.getChildren().remove(rumor));
            }
        } else if (field.hasRumor() && !this.getChildren().contains(rumor)) {
            this.getChildren().add(rumor);
            Animations.spawnEffect(rumor);
        }
        if (!field.hasMystery()) {
            if (this.getChildren().contains(mystery)) {
                Animations.removeEffect(mystery, () -> this.getChildren().remove(mystery));
            }
        } else if (field.hasMystery() && !this.getChildren().contains(mystery)) {
            this.getChildren().add(mystery);
            Animations.spawnEffect(mystery);
        }
    }

    private void createGateExpedition() {
        if (!field.hasGate()) {
            if (gateExpedition.getChildren().contains(gateGroup)) {
                Animations.removeEffect(gateGroup, () -> gateExpedition.getChildren().remove(gateGroup));
            }
        } else if (field.hasGate() && !gateExpedition.getChildren().contains(gateGroup)) {
            omen.imageView.setImage(new Image("images/interface/" + field.getTokens().getGate().getOmenState().name() + ".png", 100, 100, false, true, true));
            gateExpedition.getChildren().add(gateGroup);
            Animations.spawnEffect(gateGroup);
        }
        if (!field.hasExpedition()) {
            if (gateExpedition.getChildren().contains(expedition)) {
                Animations.removeEffect(expedition, () -> gateExpedition.getChildren().remove(expedition));
            }
        } else if (field.hasExpedition() && !gateExpedition.getChildren().contains(expedition)) {
            gateExpedition.getChildren().add(expedition);
            Animations.spawnEffect(expedition);
        }
    }

    private void createMonster() {
        monsters.getChildren().clear();

        for (Monster monster : field.getMonster()) {
            ImageView invImg = new ImageView("images/monster/" + monster.getId() + ".png");
            invImg.setFitHeight(60);
            invImg.setFitWidth(60);
            monsters.getChildren().add(invImg);
            if (!mouseOver && field.getMonster().size() > 1) {
                Label number = new Label(field.getMonster().size() + "");
                number.styleProperty().bind(Fonts.getFont(0.25, Fonts.RED, Fonts.FontTyp.BOLD));
                monsters.getChildren().add(number);
                break;
            }
        }
    }

    private void createInvestigators() {
        investigators.getChildren().clear();
        for (Investigator inv : field.getInvestigators()) {
            ImageView invImg = new ImageView("images/investigator/" + inv.getId() + ".png");
            invImg.setFitHeight(60);
            invImg.setFitWidth(60);
            investigators.getChildren().add(invImg);
        }
    }


    public static Image getBackgroundImage(Field field) {
        switch (field.getType()) {
            case CITY:
                return city;
            case WILDERNESS:
                return wilderness;
            default:
                return sea;
        }
    }
}
