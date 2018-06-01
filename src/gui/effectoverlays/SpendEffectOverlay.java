package gui.effectoverlays;

import enums.SpendType;
import javafx.scene.Group;
import javafx.scene.control.Label;
import model.effects.Spend;

public class SpendEffectOverlay extends Group {

    public SpendEffectOverlay(SpendType spendType){
        this.getChildren().add(new Label("test"));
    }
}
