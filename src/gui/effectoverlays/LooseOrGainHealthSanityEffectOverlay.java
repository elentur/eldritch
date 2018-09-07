package gui.effectoverlays;

import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.LooseOrGainHealthSanity;

public class LooseOrGainHealthSanityEffectOverlay extends Overlay {


    public LooseOrGainHealthSanityEffectOverlay(LooseOrGainHealthSanity loose) {
        super(loose);
        if(loose.getValue()==0){
            return;
        }
        String sign ="";
        if(loose.getValue()>0){
            sign="+" ;
        }
        Label value = new Label((sign + loose.getValue()) + "");
        ImageView img = null;
        value.getStyleClass().add("text-stroke-white");
        if (loose.getMonster() != null) {
            img = new ImageView(new Image("images/monster/" + loose.getMonster().getId() + ".jpg", 190, 130, true, true, false));
        } else if (loose.getInvestigator() != null) {
            img = new ImageView(new Image("images/investigator/" + loose.getInvestigator().getId() + ".jpg", 200, 200, true, true, false));
        }
        switch (loose.getSpendType()) {
            case HEALTH:
                value.styleProperty().bind(Fonts.getFont(1.5, Fonts.RED, Fonts.FontTyp.BOLD));
                break;
            case SANITY:
                value.styleProperty().bind(Fonts.getFont(1.5, Fonts.BLUE, Fonts.FontTyp.BOLD));
                break;

            default:

                break;
        }
        if (loose.getInvestigator() != null) {
            setCord(loose.getInvestigator());
        }
        this.getChildren().addAll(img, value);
        this.setMouseTransparent(true);
    }
}
