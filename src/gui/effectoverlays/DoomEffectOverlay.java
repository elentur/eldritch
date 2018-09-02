package gui.effectoverlays;

import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.effects.AdvanceDoom;
import model.effects.RetreatDoom;


public class DoomEffectOverlay extends Overlay {
private final static Image doomImage = new Image("images/effect/Doom.png",150,150,true,true,false);

    public DoomEffectOverlay(AdvanceDoom effect) {

        super(effect);
       build(-(effect.getValue()) + "",Fonts.RED);
    }

    public DoomEffectOverlay(RetreatDoom effect) {

        super(effect);
        build((effect.getValue()) + "",Fonts.GREEN);
    }

    private void build(String valueString,Color color){
        Label value = new Label(valueString);
        ImageView img ;
        value.getStyleClass().add("text-stroke-white");
        img = new ImageView(doomImage);
        value.styleProperty().bind(Fonts.getFont(1.5,color,Fonts.FontTyp.BOLD));
        setX(-800);
        setY(-350);
        this.getChildren().addAll(img,value);
        this.setMouseTransparent(true);
    }
}
