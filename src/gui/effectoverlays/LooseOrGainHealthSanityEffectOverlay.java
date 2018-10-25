package gui.effectoverlays;

import enums.SpendType;
import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.LooseOrGainHealthSanity;

public class LooseOrGainHealthSanityEffectOverlay extends Overlay {


    public LooseOrGainHealthSanityEffectOverlay(LooseOrGainHealthSanity loose) {
        super(loose);
        //loose.init();
        if(loose.getValue()==0){
            return;
        }
        if(loose.getValue()>0 && loose.getInvestigator()!=null){
            if(loose.getSpendType() == SpendType.SANITY && loose.getInvestigator().getActualSanity()==loose.getInvestigator().getSanity()){
                return;
            }
            if(loose.getSpendType() == SpendType.HEALTH && loose.getInvestigator().getActualHealth()==loose.getInvestigator().getHealth()){
                return;
            }
        }
        String sign ="";
        if(loose.getValue()>0){
            sign="+" ;
        }
        Label value = new Label((sign + loose.getValue()) + "");
        ImageView img = null;
        value.getStyleClass().add("text-stroke-white");
        if (loose.getMonster() != null) {
            img = new ImageView(new Image("images/monster/" + loose.getMonster().getId() + ".png", 190, 130, true, true, false));
        } else if (loose.getInvestigator() != null) {
            img = new ImageView(new Image("images/investigator/" + loose.getInvestigator().getId() + ".png", 200, 200, true, true, false));
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
