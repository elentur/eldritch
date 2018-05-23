package gui;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

class InfoTextButton extends TextButton {

    private final static Image backgroundImage = new Image("images/MainMenuBtn.png");
    private Label infoText;
    InfoTextButton(String key) {
       this(key,300,120);

    }
    InfoTextButton(String key, double width, double height) {
        super(key,new Rectangle(width, height, new ImagePattern(backgroundImage)));

        infoText= new Label();
        infoText.setWrapText(true);
        StackPane.setAlignment(infoText, Pos.TOP_CENTER);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        label.setTranslateY(-height/9);
        infoText.getStyleClass().addAll("text-stroke-black","stroke-thin","short-line-spacing");
        infoText.setPrefWidth(width*0.85);
        infoText.setTextAlignment(TextAlignment.CENTER);
        infoText.setAlignment(Pos.CENTER);
        this.getChildren().add(infoText);
    }

    public void setInfoText(String text){
        infoText.setText(text);
    }

    public void setStyleProperty(StringProperty style){
        infoText.styleProperty().bind(style);
    }


}
