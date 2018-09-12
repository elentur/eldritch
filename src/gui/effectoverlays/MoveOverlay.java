package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.GainAsset;
import model.effects.Move;

public class MoveOverlay extends Overlay {


    public MoveOverlay(Move effect) {
        super(effect);
        ImageView img = new ImageView(new Image("images/investigator/"+effect.getInvestigator().getId()+".jpg", 100, 100, true, true, false));

        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }

}
