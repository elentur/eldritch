package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class TextField extends TextArea {

    public TextField( String text){
        super(text.trim());
        setWrapText(true);
        styleProperty().bind(Fonts.getFont(0.25, Fonts.DARK, Fonts.FontTyp.NORMAL));
        getStyleClass().add("eldritch-text-field");
        setEditable(false);


    }


}
