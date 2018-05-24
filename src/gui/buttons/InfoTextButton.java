package gui.buttons;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class InfoTextButton extends TextButton {

    private final static Image backgroundImage = new Image("images/MainMenuBtn.png",300,120,false,true,false);
    private Label infoText;

    public InfoTextButton(String key) {
        super(key,backgroundImage);

        infoText= new Label();
        infoText.setWrapText(true);
        infoText.setMouseTransparent(true);
        StackPane.setAlignment(infoText, Pos.TOP_CENTER);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        label.setTranslateY(-12);
        infoText.getStyleClass().addAll("text-stroke-black","stroke-thin","short-line-spacing");
        infoText.setPrefWidth(backgroundImage.getWidth()*0.85);
        infoText.setPrefHeight(backgroundImage.getHeight()*0.5);
        infoText.setTextAlignment(TextAlignment.CENTER);
        infoText.setAlignment(Pos.CENTER);
     //   infoText.setBorder(new Border(new BorderStroke(Fonts.RED, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.MEDIUM)));
        this.getChildren().add(infoText);
    }

    public void setInfoText(String text){
        infoText.setText(text);
    }

    public void setStyleProperty(StringProperty style){
        infoText.styleProperty().bind(style);
    }


}
