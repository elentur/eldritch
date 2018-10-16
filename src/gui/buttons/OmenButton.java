package gui.buttons;

import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class OmenButton extends Button {
    private final static Image tokenImage = new Image("images/gameBoard/Eldritch.png", 100, 100, true, true, false);
    private final  ImageView token;
    private Label numberOfTokens;
    public OmenButton(Image image) {
        super(image);
        token = new ImageView(tokenImage);
        token.setVisible(false);
        actualNode.setPickOnBounds(true);
        setDisabledEffect(null);
        setEnabled(false);
        numberOfTokens= new Label();
        numberOfTokens.styleProperty().bind(Fonts.getFont(0.22, Fonts.WHITE, Fonts.FontTyp.BOLD));
        numberOfTokens.setPrefWidth(50);
        numberOfTokens.setPrefHeight(50);
        numberOfTokens.setAlignment(Pos.CENTER);
        numberOfTokens.setTextAlignment(TextAlignment.CENTER);
        numberOfTokens.visibleProperty().bindBidirectional(token.visibleProperty());
        this.getChildren().addAll(token,numberOfTokens);
    }

    public void setLabel(int i){
        if(i==0){
            token.setVisible(false);
        }else {
            token.setVisible(true);
        }
    }


}
