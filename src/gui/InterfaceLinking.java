package gui;

import Service.GameService;
import enums.EffectTyps;
import gamemechanics.Action;
import gamemechanics.choice.*;
import gamemechanics.encounter.CombatEncounter;
import gui.choice.*;
import gui.effectoverlays.*;
import gui.encounters.ActionGui;
import gui.encounters.CombatEncounterGui;
import gui.encounters.EncounterGui;
import gui.gameboard.GameBoardGUI;
import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.effects.*;

@Log
public class InterfaceLinking {

    @Getter
    static InterfaceLinking instance = new InterfaceLinking();

    public static StackPane root;
    public static Interface interfaceGui;
    public static GameBoardGUI gameBoardGUI;
    private static Stage primaryStage;
    @Getter
    private static BooleanProperty lockGameBoard;


    public static void init(Stage stage,StackPane root, Interface interfaceGui, GameBoardGUI gameBoardGUI) {
        primaryStage = stage;
        InterfaceLinking.root = root;
        InterfaceLinking.interfaceGui = interfaceGui;
        InterfaceLinking.gameBoardGUI=gameBoardGUI;
        gameBoardGUI.mouseTransparentProperty().bind(lockGameBoard);

    }

    private InterfaceLinking() {
        GameService game = GameService.getInstance();
        game.getChoiceProperty().addListener((a,b,c) -> startChoiceDialog(game.getChoiceProperty().getValue()));
        game.getEncounterProperty().addListener((a,b,c) -> startEncounterDialog(game.getEncounterProperty().getValue()));
        lockGameBoard = new SimpleBooleanProperty(false);
        game.getInsertions().addListener((ListChangeListener<? super Effect>) e -> {
            lockGameBoard.setValue(false);
            System.out.println( game.getInsertions());
            if (!e.getList().isEmpty()) {
                Effect effect = e.getList().get(0);
                if (!effect.isExecuted()) {
                    //lockGameBoard.setValue(effect.getEffectType().equals(EffectTyps.NEXT_INVESTIGATOR)
                  //  || effect.getEffectType().equals(EffectTyps.SWITCH_PHASE));
                    Effect found = game.getInsertions().stream().filter(effect1 -> effect1.getEffectType().equals(EffectTyps.NEXT_INVESTIGATOR)
                              || effect1.getEffectType().equals(EffectTyps.SWITCH_PHASE)
                            || effect1.getEffectType().equals(EffectTyps.MOVE)).findAny().orElse(null);
                    lockGameBoard.setValue(found!=null);
                    System.out.println(lockGameBoard.getValue() + " " + gameBoardGUI.mouseTransparentProperty().getValue() );
                    createEffectOverlay(effect);
                }
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
        switch (effect.getEffectType()) {
            case LOOSE_OR_GAIN_HEALTH_SANITY:
                Animations.effectOverlayAnimations(new LooseOrGainHealthSanityEffectOverlay((LooseOrGainHealthSanity) effect), primaryStage, effect);
                break;
            case SPEND:
                Animations.effectOverlayAnimations(new SpendEffectOverlay((Spend) effect), primaryStage, effect);
                break;
            case SPAWN_CLUE:
                Animations.effectOverlayAnimations(new SpawnClueEffectOverlay((SpawnClue) effect), primaryStage, effect);
                break;
            case SPAWN_ELDRITCH_TOKEN:
                Animations.effectOverlayAnimations(new SpawnEldritchTokenEffectOverlay((SpawnEldritchToken) effect), primaryStage, effect);
                break;
            case SPAWN_GATE:
                Animations.effectOverlayAnimations(new SpawnGateEffectOverlay((SpawnGate) effect), primaryStage, effect);
                break;
            case CLOSE_GATE:
                Animations.effectOverlayAnimations(new CloseGateEffectOverlay((CloseGate) effect), primaryStage, effect);
                break;
            case REMOVE_ELDRITCH_TOKEN:
                Animations.effectOverlayAnimations(new RemoveEldritchTokenEffectOverlay((RemoveEldritchToken) effect), primaryStage, effect);
                break;
            case DISCARD_MONSTER:
                Animations.effectOverlayAnimations(new DiscardMonsterOverlay((DiscardMonster) effect), primaryStage, effect);
                break;
            case GAIN_CLUE:
                Animations.effectOverlayAnimations(new GainEffectOverlay((GainClue) effect), primaryStage, effect);
                break;
            case GAIN_FOCUS:
                Animations.effectOverlayAnimations(new GainEffectOverlay((GainFocus) effect), primaryStage, effect);
                break;
            case GAIN_TICKET:
                Animations.effectOverlayAnimations(new GainEffectOverlay((GainTicket) effect), primaryStage, effect);
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
            case ADD_ELDRITCH_TO_MYSTERY:
                Animations.effectOverlayAnimations(new AddEldritchToMysteryOverlay((AddEldritchToMystery) effect), primaryStage, effect);
                break;
            case SPAWN_MONSTER:
                Animations.effectOverlayAnimations(new SpawnMonsterEffectOverlay((SpawnMonster) effect), primaryStage, effect);
                break;
            case RETREAT_OMEN:
                Animations.effectOverlayAnimations(new OmenEffectOverlay((RetreatOmen) effect), primaryStage, effect);
                break;
            case GAIN_ASSET:
                Animations.effectOverlayAnimations(new AssetOverlay((GainAsset) effect), primaryStage, effect);
                break;
            case GAIN_ARTIFACT:
                Animations.effectOverlayAnimations(new ArtifactOverlay((GainArtifact) effect), primaryStage, effect);
                break;
            case SWITCH_PHASE:
                Animations.effectOverlayPhaseSwitch(new SwitchPhaseOverlay((SwitchPhase) effect), primaryStage, effect);
                break;
            case MONSTER_SURGE:
                effect.execute();
                GameService.getInstance().getInsertions().remove(effect);
                break;
            case AND:
                effect.execute();
                GameService.getInstance().getInsertions().remove(effect);
                break;
            case MOVE:
                Animations.effectOverlayMove(new MoveOverlay((Move) effect), primaryStage, (Move)effect);
                break;
            case TRADE:
                GameService.getInstance().getInsertions().remove(effect);
                effect.execute();
                break;

            default:
                Platform.runLater(() -> {
                    GameService.getInstance().getInsertions().remove(effect);
                    effect.execute();
                });
                break;
        }
    }

    public void startChoiceDialog(Choice choice) {
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
            case MONSTER_CHOICE:
                dlg = new MonsterChoiceGUI((MonsterChoice) choice);
                break;
            case INVESTIGATOR_CHOICE:
                dlg = new InvestigatorChoiceGUI((InvestigatorChoice) choice);
                break;
            case TRADE:
                dlg = new TradeChoiceGUI((TradeChoice) choice);
                break;
            case RESERVE_CHOICE:
                dlg = new ReserveChoiceGUI((ReserveChoice) choice);
                break;
            case ITEM_CHOICE:
                dlg = new ItemChoiceGUI((ItemChoice) choice);
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

    private void startEncounterDialog(gamemechanics.encounter.Encounter encounter) {
        if (encounter == null) {
            return;
        }
        DialogGui dlg = null;
        switch (encounter.getEncounterType()) {
            case COMBAT_ENCOUNTER:
                dlg = new CombatEncounterGui((CombatEncounter) encounter);
                break;
            case ACTION:
                dlg = new ActionGui((Action) encounter);
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
