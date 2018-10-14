package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.AddEldritchToMystery;

public class AddEldritchToMysteryOverlay extends Overlay {
private final static Image eldritch = new Image("images/gameBoard/Eldritch.png",150,150,true,true,false);

    public AddEldritchToMysteryOverlay(AddEldritchToMystery effect) {

        super(effect);

        ImageView img ;
        img = new ImageView(eldritch);
        setX(-650);
        setY(-450);
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }
}
