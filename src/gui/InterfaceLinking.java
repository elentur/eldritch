package gui;

import Service.GameService;
import gamemechanics.CombatEncounter;
import gamemechanics.Encounter;
import gamemechanics.choice.Choice;
import gamemechanics.choice.YesNoChoice;
import gui.choice.YesNoDialog;
import gui.effectoverlays.LooseEffectOverlay;
import gui.effectoverlays.SpendEffectOverlay;
import gui.encounters.CombatEncounterGui;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
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

    }


    private void createEffectOverlay(Effect effect) {
        if (effect == null) {
            return;
        }
        Group group=null;
        if (effect instanceof Spend) {
             group = new SpendEffectOverlay( (Spend) effect);
        }else if (effect instanceof Loose) {
            group = new LooseEffectOverlay( (Loose) effect);
        }
        Animations.effectOverlayAnimations(group,primaryStage,effect);
    }


    public static void init(Stage stage) {
        primaryStage = stage;
        root = (StackPane) primaryStage.getScene().getRoot();
    }


    private void startChoiceDialog(Choice choice) {
        if (choice == null) {
            return;
        }
        DialogGui dlg = null;
        if (choice instanceof YesNoChoice) {
            dlg = new YesNoDialog((YesNoChoice) choice);

        }
        if(dlg==null){
            return;
        }
        root.getChildren().add(dlg);
        dlg.showAndWait();
    }

    private void startEncounterDialog(Encounter encounter) {
        if (encounter == null) {
            return;
        }
        DialogGui dlg = null;
        if (encounter instanceof CombatEncounter) {
             dlg = new CombatEncounterGui((CombatEncounter) encounter);

        }
        if(dlg==null){
            return;
        }
        root.getChildren().add(dlg);
        dlg.showAndWait();
        System.out.println("fertig");

    }
}
