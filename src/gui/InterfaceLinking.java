package gui;

import Service.GameService;
import gamemechanics.choice.*;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import gui.choice.*;
import gui.effectoverlays.*;
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
import model.effects.*;

@Log
public class InterfaceLinking {

    static InterfaceLinking instance = new InterfaceLinking();

    public static StackPane root;
    public static Interface interfaceGui;
    public static GameBoardGUI gameBoardGUI;
    private static Stage primaryStage;


    public static void init(Stage stage) {
        primaryStage = stage;
        root = (StackPane) primaryStage.getScene().getRoot();
        for (Node n : root.getChildren()) {
            if (n instanceof Interface) {
                interfaceGui = (Interface) n;
            } else if (n instanceof GameBoardGUI) {
                gameBoardGUI = (GameBoardGUI) n;
            }
        }
    }

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
            case SPAWN_CLUE:
                Animations.effectOverlayAnimations(new SpawnClueEffectOverlay((SpawnClue) effect), primaryStage, effect);
                break;
            case SPAWN_GATE:
                Animations.effectOverlayAnimations(new SpawnGateEffectOverlay((SpawnGate) effect), primaryStage, effect);
                break;
            case GAIN_CLUE:
                Animations.effectOverlayAnimations(new GainEffectOverlay((GainClue) effect), primaryStage, effect);
                break;
            case IMPROVE:
                Animations.effectOverlayAnimations(new ImproveEffectOverlay((Improve) effect), primaryStage, effect);
                break;
            case ADVANCE_DOOM:
                Animations.effectOverlayAnimations(new DoomEffectOverlay((AdvanceDoom) effect), primaryStage, effect);
                break;
            case RETREAT_DOOM:
                Animations.effectOverlayAnimations(new DoomEffectOverlay((RetreatDoom) effect), primaryStage, effect);
                break;
            case ADVANCE_OMEN:
                Animations.effectOverlayAnimations(new OmenEffectOverlay((AdvanceOmen) effect), primaryStage, effect);
                break;
            case RETREAT_OMEN:
                Animations.effectOverlayAnimations(new OmenEffectOverlay((RetreatOmen) effect), primaryStage, effect);
                break;
            case RANDOM_ASSET:
                Animations.effectOverlayAnimations(new AssetOverlay((GainAsset) effect), primaryStage, effect);
                break;
            case SWITCH_PHASE:
                Animations.effectOverlayPhaseSwitch(new SwitchPhaseOverlay((SwitchPhase) effect), primaryStage, effect);
                break;
            case AND:
                Platform.runLater(() -> {
                    GameService.getInstance().getInsertions().remove(effect);
                    effect.execute();
                });

                break;
            case OR:
                Platform.runLater(() -> {
                    GameService.getInstance().getInsertions().remove(effect);
                    effect.execute();
                });
                break;
            case MONSTER_FLOOD:
                Platform.runLater(() -> {
                    GameService.getInstance().getInsertions().remove(effect);
                    effect.execute();
                });
                break;
            default:

                Platform.runLater(() -> GameService.getInstance().getInsertions().remove(effect));
                effect.execute();
                break;

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
            case RESERVE_CHOICE:
                dlg = new ReserveChoiceGUI((ReserveChoice) choice);
                break;
            case INFORMATION:
                dlg = new InformationDialog((InformationChoice) choice);
                break;
            case EFFECT:
                dlg = new EffectChoiceGUI((EffectChoice) choice);
                break;
        }
        if (dlg == null) {
            return;
        }
        root.getChildren().add(root.getChildren().size() - 1, dlg);
        // Platform.runLater(dlg::showAndWait);
        dlg.showAndWait();
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
        root.getChildren().add(root.getChildren().size() - 1, dlg);
        //Platform.runLater(dlg::showAndWait);
        dlg.showAndWait();

    }
}
