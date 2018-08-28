package gui.interfaceelements;

import Service.GameService;
import enums.SpendType;
import gui.Effects;
import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import model.Item.Investigator;

public class AncientOneGUI extends Group {
    private final static Image frameImage = new Image("images/interface/AncientOneInterface.png", 250, 250, true, true, true);

    private final ImageView portrait;

    public AncientOneGUI() {
        ImageView frame = new ImageView(frameImage);
        portrait = new ImageView();
        portrait.setTranslateX(20);
        portrait.setX(-80);
        Circle clip = new Circle(65);
        clip.setCenterX(45);
        clip.setCenterY(45);
        portrait.setClip(clip);



        this.getChildren().addAll(portrait, frame);
        this.setEffect(Effects.dropShadow);
        update();
    }

    public void update() {
        portrait.setImage(new Image("images/ancientones/AzathothBig.jpg", 212, 104, true, true, true));

    }
}
