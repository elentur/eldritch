package gui;

import Service.GameService;
import com.sun.deploy.util.StringUtils;
import container.ItemContainer;
import gamemechanics.choice.Choice;
import gamemechanics.choice.YesNoChoice;
import gui.choice.YesNoDialog;
import javafx.collections.ListChangeListener;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Asset;

import java.io.File;
import java.security.acl.Group;
import java.util.Objects;

@Log
public class InterfaceLinking {

    static InterfaceLinking instance = new InterfaceLinking();


    private InterfaceLinking( ) {
        GameService game = GameService.getInstance();
        game.getChoiceProperty().addListener(e -> startChoiceDialog(game.getChoiceProperty().getValue()));
        game.getInsertions().addListener((ListChangeListener<? super Effect>) e -> {
            if (!e.getList().isEmpty()) {
                createEffectOverlay(e.getList().get(0));
            }
        });
    }


    private void createEffectOverlay(Effect effect) {
        log.info(effect.toString());
        String name = effect.getClass().getSimpleName();

        File f = new File("./src/gui/effectoverlays/");

        try {
             Group g = (Group) Class.forName("gui.effectoverlays." + name +"EffectOverlay").newInstance();

        }catch (Exception e){

        }
    }


    public static void init() {
    }

    private void startChoiceDialog(Choice choice) {
        if (choice == null) {
            return;
        }
        if (choice instanceof YesNoChoice) {
            DialogGui dlg = new YesNoDialog((YesNoChoice) choice);
            dlg.showAndWait();
        }

    }
}
