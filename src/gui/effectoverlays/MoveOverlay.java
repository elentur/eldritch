package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.Move;

public class MoveOverlay extends Overlay {


    private final Move move;

    public MoveOverlay(Move effect) {
        super(effect);
        this.move = effect;
        ImageView img = new ImageView(new Image("images/investigator/"+effect.getInvestigator().getId()+".png", 100, 100, true, true, false));

        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }

    @Override
    public int init() {
        move.init();
        return super.init();
    }
}
