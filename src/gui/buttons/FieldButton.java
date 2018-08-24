package gui.buttons;

import gui.Fonts;
import javafx.beans.Observable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.Field;
import model.Item.Investigator;

public class FieldButton extends Button {

    private final static Image city = new Image("images/gameBoard/CitySpace.png", 120, 120, true, true, false);
    private final static Image sea = new Image("images/gameBoard/SeaSpace.png", 120, 120, true, true, false);
    private final static Image wilderness = new Image("images/gameBoard/WildernessSpace.png", 120, 120, true, true, false);

    private final Field field;
    private final FlowPane investigators;

    public FieldButton(Field field, double x, double y) {
        super(getBackgroundImage(field));
        this.field = field;
        this.setTranslateX(x);
        this.setTranslateY(y);
        investigators = new FlowPane(5,5);
        investigators.setTranslateY(120);
        this.getChildren().add(investigators);
        //  this.setBorder(new Border(new BorderStroke(Fonts.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.MEDIUM)));
        field.getUpdate().addListener(e -> update());
        update();
    }

    private void update() {
        createInvestigators();
    }

    private void createInvestigators() {
        for(Investigator inv : field.getInvestigators()){
            ImageView invImg = new ImageView("images/investigator/"+ inv.getId()+".jpg");
            invImg.setFitHeight(60);
            invImg.setFitWidth(60);
            investigators.getChildren().add(invImg);
        }
    }


    public static Image getBackgroundImage(Field field) {
        switch (field.getType()) {
            case CITY:
                return city;
            case WILDERNESS:
                return wilderness;
            default:
                return sea;
        }
    }
}
