package gui.gameboard;


import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import utils.ResourceUtil;

public class CityLabel extends Group {

    public CityLabel(String key, double x, double y){
        this.setLayoutX(x);
        this.setLayoutY(y);
        Label image = new Label();
        image.getStyleClass().add("city-label");
        image.setMinWidth(518);
        image.setMinHeight(154);
        this.getChildren().add(image);

        Label name = new Label();
        name.setMinWidth(518);
        name.setMinHeight(70);
        name.setLayoutX(20);
        name.setLayoutY(10);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setAlignment(Pos.CENTER);
        name.styleProperty().bind(Fonts.getFont(0.35, Fonts.DARK, Fonts.FontTyp.BOLD));
        name.setText(ResourceUtil.get(key,"fieldid"));

        this.getChildren().add(name);

        Label improve = new Label();
        improve.setMinWidth(518);
        improve.setMinHeight(70);
        improve.setLayoutX(20);
        improve.setLayoutY(80);
        improve.setTextAlignment(TextAlignment.CENTER);
        improve.setAlignment(Pos.CENTER);
        improve.styleProperty().bind(Fonts.getFont(0.25, Fonts.DARK, Fonts.FontTyp.NORMAL));
        improve.setText( ResourceUtil.get(key.replace("}","")+"_improve}","fieldid"));

        this.getChildren().add(improve);
    }

}
