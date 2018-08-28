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

import java.util.InvalidPropertiesFormatException;

public class ActiveInvestigatorGUI extends Group {
    private final static Image frameImage = new Image("images/interface/InvestigatorFrame.png", 250, 242, true, true, true);

    private final ImageView portrait;
    private final Label name;
    private final TokenImage healthImage;
    private final TokenImage sanityImage;
    private final TokenImage clueImage;
    private final TokenImage focusImage;
    private final TokenImage trainImage;
    private final TokenImage shipImage;

    public ActiveInvestigatorGUI() {
        ImageView frame = new ImageView(frameImage);
        portrait = new ImageView();
        // frameImage.setClip(frameImage);
        Circle clip = new Circle(100);
        clip.setCenterX(125);
        clip.setCenterY(121);
        portrait.setClip(clip);

        name = new Label();
        name.styleProperty().bind(Fonts.getFont(0.22,Fonts.DARK, Fonts.FontTyp.ITALIC));
        name.setPrefWidth(250);
        name.setPrefHeight(50);
        name.setAlignment(Pos.CENTER);
        name.setTranslateY(180);
        name.setTextAlignment(TextAlignment.CENTER);

        healthImage = new TokenImage(SpendType.HEALTH,50);
        healthImage.setTranslateX(190);
        healthImage.setTranslateY(130);

       sanityImage = new TokenImage(SpendType.SANITY,50);
        sanityImage.setTranslateX(200);
        sanityImage.setTranslateY(80);

        clueImage = new TokenImage(SpendType.CLUE,50);
        clueImage.setTranslateX(10);
        clueImage.setTranslateY(130);

        focusImage = new TokenImage(SpendType.FOCUS,50);
        focusImage.setTranslateX(0);
        focusImage.setTranslateY(80);

        trainImage = new TokenImage(SpendType.TRAIN_TICKET,50);
        trainImage.setTranslateX(80);
        trainImage.setTranslateY(-15);

        shipImage = new TokenImage(SpendType.SHIP_TICKET,50);
        shipImage.setTranslateX(130);
        shipImage.setTranslateY(-15);


        this.getChildren().addAll(portrait, frame,name,healthImage,sanityImage,clueImage,focusImage,trainImage,shipImage);
        this.setEffect(Effects.dropShadow);
    }

    public void update() {
        Investigator inv = GameService.getInstance().getActiveInvestigator();
        if (inv != null) {
            portrait.setImage(new Image("images/investigator/" + inv.getId() + ".jpg", 250, 242, true, true, true));
            name.setText(inv.getName());
            healthImage.setValue(inv.getActualHealth()+"");
            sanityImage.setValue(inv.getActualSanity()+"");
            clueImage.setValue("1");
            focusImage.setValue("1");
            trainImage.setValue("1");
            shipImage.setValue("1");
        }
    }
}
