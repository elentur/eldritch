package gui.effectoverlays;

import gui.Fonts;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.Spend;

public class SpendEffectOverlay extends Overlay {
private final static Image sanityImage = new Image("images/Sanity.png",150,150,true,true,false);
    private final static Image healthImage = new Image("images/Health.png",150,150,true,true,false);
    private final static Image clueImage = new Image("images/Clue.png",150,150,true,true,false);

    public SpendEffectOverlay(Spend spend) {

        Label value = new Label((-spend.getValue()) + "");
        ImageView img ;
        value.getStyleClass().add("text-stroke-white");
        switch (spend.getSpendType()) {
            case HEALTH:
                value.styleProperty().bind(Fonts.getFont(1.5,Fonts.RED,Fonts.FontTyp.BOLD));

              img = new ImageView(healthImage);
                break;
            case SANITY:
                value.styleProperty().bind(Fonts.getFont(1.5,Fonts.BLUE,Fonts.FontTyp.BOLD));
                img = new ImageView(sanityImage);
                break;
            case CLUE:
                img = new ImageView(clueImage);
                value.styleProperty().bind(Fonts.getFont(1.5,Fonts.GREEN,Fonts.FontTyp.BOLD));
                break;
            default:
                img= null;
                break;
        }
        this.getChildren().addAll(img,value);
        this.setMouseTransparent(true);
    }
}
