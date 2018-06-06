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


        Tooltip tooltip = new Tooltip();
        tooltip.getStyleClass().add("bonus");
        Tooltip.install(imageView, tooltip);
        tooltip.styleProperty().bind(Fonts.getFont(0.17,Fonts.DARK,Fonts.FontTyp.NORMAL));
        tooltip.setText(bonus.getParentName()+"\n"+bonus.getText());
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(200);


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
