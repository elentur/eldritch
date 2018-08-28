package gui.interfaceelements;

import enums.SpendType;
import gui.Effects;
import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.TextAlignment;
import model.Item.Token;



public class TokenImage extends Group {

    private final Label valueLabel;
    public TokenImage(SpendType spendType, double size){
        ImageView imageView = new ImageView("images/interface/" + spendType.getKey().replaceAll("[{}\\$]", "")+".png");
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        valueLabel = new Label();
        valueLabel.setPrefWidth(size);
        valueLabel.setPrefHeight(size);
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        valueLabel.styleProperty().bind(Fonts.getFont(0.25,Fonts.WHITE, Fonts.FontTyp.BOLD));
        valueLabel.getStyleClass().add("text-stroke-black");
        this.getChildren().addAll(imageView,valueLabel);

       // valueLabel.setBorder(new Border(new BorderStroke(Fonts.RED, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.MEDIUM)));

    }

    public void setValue(String v){
        valueLabel.setText(v);
    }
}
