package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.AdvanceOmen;
import model.effects.RetreatOmen;


public class OmenEffectOverlay extends Overlay {
private final static Image omenAdvanceImage = new Image("images/effect/Omen.png",150,150,true,true,false);
    private final static Image omenRetreatImage = new Image("images/effect/Omen_Retreat.png",150,150,true,true,false);

    public OmenEffectOverlay(AdvanceOmen effect) {

        super(effect);
       build(omenAdvanceImage);
    }

    public OmenEffectOverlay(RetreatOmen effect) {

        super(effect);
        build(omenRetreatImage);
    }

    private void build(Image image){

        ImageView img ;

        img = new ImageView(image);
        setX(800);
        setY(-350);
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }
}
