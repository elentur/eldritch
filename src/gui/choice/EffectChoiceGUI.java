package gui.choice;

import Service.GameService;
import gamemechanics.choice.EffectChoice;
import gui.ItemScrollPane;
import gui.buttons.InfoTextButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Effect;

import java.util.List;

public class EffectChoiceGUI extends ChoiceDialog {


    public EffectChoiceGUI(EffectChoice choice) {
        super(0.6, 0.6, choice);

        List<Effect> effects = choice.getEffects();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Effect effect : effects) {
            InfoTextButton button= new InfoTextButton("${effect_button}");
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    GameService.getInstance().addEffect(effect);
                    close();
                }
            });
            button.setInfoText(effect.getText());
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);
    }


}
