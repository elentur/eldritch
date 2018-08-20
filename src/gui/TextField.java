package gui;

import javafx.scene.control.TextArea;

public class TextField extends TextArea {

    public TextField( String text){
        super(text);
        setWrapText(true);
        styleProperty().bind(Fonts.getFont(0.25, Fonts.DARK, Fonts.FontTyp.NORMAL));
        getStyleClass().add("eldritch-text-field");
        setEditable(false);
    }
}
