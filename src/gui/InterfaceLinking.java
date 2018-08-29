package gui;

import Service.GameService;
import gamemechanics.choice.*;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import gui.choice.EncounterChoiceGUI;
import gui.choice.InformationDialog;
import gui.choice.MonsterChoiceGUI;
import gui.choice.YesNoDialog;
import gui.effectoverlays.LooseEffectOverlay;
import gui.effectoverlays.SpendEffectOverlay;
import gui.encounters.CombatEncounterGui;
import gui.encounters.EncounterGui;
import gui.gameboard.GameBoardGUI;
import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import model.Effect;
import model.effects.Loose;
import model.effects.Spend;

@Log
public class InterfaceLinking {

    static InterfaceLinking instance = new InterfaceLinking();

    private static StackPane pane;

    public static StackPane root;
    public static Interface interfaceGui;
    public static GameBoardGUI gameBoardGUI;
    private static Stage primaryStage;


    private InterfaceLinking() {
        GameService game = GameService.getInstance();
        game.getChoiceProperty().addListener(e -> startChoiceDialog(game.getChoiceProperty().getValue()));
        game.getEncounterProperty().addListener(e -> startEncounterDialog(game.getEncounterProperty().getValue()));
        game.getInsertions().addListener((ListChangeListener<? super Effect>) e -> {
            if (!e.getList().isEmpty()) {
                createEffectOverlay(e.getList().get(0));
            }
        });
        game.getActiveInvestigatorProperty().addListener(e -> updateInvestigatorInterface());


    }

    private void updateInvestigatorInterface() {
        ActiveInvestigatorGUI activeInvestigatorGUI = interfaceGui.getActiveInvestigatorGUI();
        activeInvestigatorGUI.update();

        InactiveInvestigatorsGUI inactiveInvestigatorsGUI = interfaceGui.getInactiveInvestigatorsGUI();
        inactiveInvestigatorsGUI.update();
    }


    private void createEffectOverlay(Effect effect) {
        if (effect == null) {
            return;
        }
        switch (effect.getEffectTyp()) {
            case LOOSE:
                Animations.effectOverlayAnimations(new LooseEffectOverlay((Loose) effect), primaryStage, effect);
                break;
            case SPEND:
                Animations.effectOverlayAnimations(new SpendEffectOverlay((Spend) effect), primaryStage, effect);
                break;
            default:
//                GameService.getInstance().getInsertions().remove(effect);
                break;

        }


    }


    public static void init(Stage stage) {
        primaryStage = stage;
        root = (StackPane) primaryStage.getScene().getRoot();
        for(Node n: root.getChildren()){
            if(n instanceof Interface){
                interfaceGui= (Interface)n;
            }else if(n instanceof GameBoardGUI){
                gameBoardGUI= (GameBoardGUI) n;
            }
        }
    }


    private void startChoiceDialog(Choice choice) {
        if (choice == null) {
            return;
        }
        DialogGui dlg = null;
        switch (choice.getChoiceType()) {
            case YES_NO:
                dlg = new YesNoDialog((YesNoChoice) choice);
                break;
            case ENCOUNTER:
                dlg = new EncounterChoiceGUI((EncounterChoice) choice);
                break;
            case COMBAT_ENCOUNTER:
                dlg = new MonsterChoiceGUI((MonsterChoice) choice);
                break;
            case INFORMATION:
                dlg = new InformationDialog((InformationChoice) choice);
                break;
        }
        if (dlg == null) {
            return;
        }
        root.getChildren().add(root.getChildren().size()-1,dlg);
        Platform.runLater(dlg::showAndWait);
    }

    private void startEncounterDialog(Encounter encounter) {
        if (encounter == null) {
            return;
        }
        DialogGui dlg = null;
        switch (encounter.getEncounterType()) {
            case COMBAT_ENCOUNTER:
                dlg = new CombatEncounterGui((CombatEncounter) encounter);
                break;
            default:
                dlg = new EncounterGui(encounter);
                break;
        }
        if (dlg == null) {
            return;
        }
        root.getChildren().add(root.getChildren().size()-1,dlg);
        Platform.runLater(dlg::showAndWait);

    }
}
