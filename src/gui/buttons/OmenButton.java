package gui.buttons;

import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class OmenButton extends Button {
    private final static Image tokenImage = new Image("images/gameBoard/Eldritch.png", 50, 50, true, true, false);
    private final  ImageView token;
    private Label numberOfTokens;
    public OmenButton(Image image) {
        super(image);
        token = new ImageView(tokenImage);
        token.setVisible(false);
        token.setTranslateX(25);
        token.setTranslateY(25);
        actualNode.setPickOnBounds(true);
        setDisabledEffect(null);
        setEnabled(false);
        numberOfTokens= new Label();
        numberOfTokens.styleProperty().bind(Fonts.getFont(0.35, Fonts.RED, Fonts.FontTyp.BOLD));
        numberOfTokens.getStyleClass().add("text-stroke-black");
        numberOfTokens.setPrefWidth(50);
        numberOfTokens.setPrefHeight(50);
        numberOfTokens.setTranslateX(25);
        numberOfTokens.setTranslateY(25);
        numberOfTokens.setAlignment(Pos.CENTER);
        numberOfTokens.setTextAlignment(TextAlignment.CENTER);
        numberOfTokens.setOpacity(1);
        numberOfTokens.visibleProperty().bindBidirectional(token.visibleProperty());
        ((Group)actualNode).getChildren().addAll(token,numberOfTokens);
    }

    public void setLabel(int i){
        if(i==0){
            token.setVisible(false);
        }else {
            token.setVisible(true);
            numberOfTokens.setText(i+"");
        }
    }


}
