package gui.gameboard;


import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import utils.ResourceUtil;

public class FieldLabel extends Label {

    public  FieldLabel(String key, double x, double y){
        this.setLayoutX(x+120);
        this.setLayoutY(y+105);
        this.setMinWidth(66);
        this.setMinHeight(58);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setAlignment(Pos.CENTER);
        this.styleProperty().bind(Fonts.getFont(0.35, Fonts.DARK, Fonts.FontTyp.BOLD));
        this.setText(ResourceUtil.get(key,"fieldid").split(" ")[1]);
        this.getStyleClass().add("field-label");

    }

}
