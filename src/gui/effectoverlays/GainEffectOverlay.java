package gui.effectoverlays;

import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.GainClue;

public class GainEffectOverlay extends Overlay {
private final static Image sanityImage = new Image("images/effect/Sanity.png",150,150,true,true,false);
    private final static Image healthImage = new Image("images/effect/Health.png",150,150,true,true,false);
    private final static Image clueImage = new Image("images/effect/Clue.png",150,150,true,true,false);

    public GainEffectOverlay(GainClue effect) {

        super(effect);
        Label value = new Label((effect.getValue()) + "");
        ImageView img ;
        value.getStyleClass().add("text-stroke-white");
        img = new ImageView(clueImage);
        value.styleProperty().bind(Fonts.getFont(1.5,Fonts.GREEN,Fonts.FontTyp.BOLD));
        setCord(effect.getInvestigator());
        this.getChildren().addAll(img,value);
        this.setMouseTransparent(true);
    }
}
