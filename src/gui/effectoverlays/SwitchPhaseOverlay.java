package gui.effectoverlays;

import Service.GameService;
import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.effects.SwitchPhase;

public class SwitchPhaseOverlay extends Overlay {

    public SwitchPhaseOverlay(SwitchPhase switchPhase) {
        super(switchPhase);
        Label value = new Label(GameService.getInstance().getPhases().getNextPhase().getText());
        Color color=Fonts.WHITE;
        switch(GameService.getInstance().getPhases().getNextPhase()){
            case ACTION:
                color=Fonts.RED;
                break;
            case ENCOUNTER:
                color=Fonts.GREEN;
                break;
            case MYTHOS:
                color=Fonts.BLUE;
                break;
        }
        value.styleProperty().bind(Fonts.getFont(2.0,color, Fonts.FontTyp.BOLD));

        this.getChildren().addAll(value);
        this.setMouseTransparent(true);
    }
}
