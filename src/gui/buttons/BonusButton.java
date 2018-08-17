package gui.buttons;

import gui.Fonts;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import model.Item.Bonus;

public class BonusButton extends Button {

    private final Bonus bonus;

    public BonusButton(Bonus bonus) {
        super(createImage(bonus));
        this.bonus = bonus;
        setTooltipText(bonus.getParentName()+"\n"+bonus.getText());


    }

    private static Image createImage(Bonus bonus) {
        switch (bonus.getBonusType()) {
            case DICE_RESULT:
                return new Image("images/bonus/shift.png", 100, 100, true, true, true);
            case REPEAT_ROLL:
                return new Image("images/bonus/reroll.png", 100, 100, true, true, true);
            case SWITCH_SKILL:
                return new Image("images/bonus/switch.png", 100, 100, true, true, true);
            case ADDITIONAL_DICE:
                return new Image("images/bonus/additional.png", 100, 100, true, true, true);
            default:
                return new Image("images/cancel.png", 100, 100, true, true, true);

        }
    }


}
