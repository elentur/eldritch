package gui.GameBoard;


import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import utils.ResourceUtil;

public class ExpeditionLabel extends Group {

    public ExpeditionLabel(String key, double x, double y){
        this.setLayoutX(x-20);
        this.setLayoutY(y-10);
        Label image = new Label();
        image.getStyleClass().add("expedition-label");
        image.setMinWidth(558);
        image.setMinHeight(170);
        this.getChildren().add(image);

        Label name = new Label();
        name.setMinWidth(518);
        name.setMinHeight(70);
        name.setLayoutX(70);
        name.setLayoutY(40);
        name.setRotate(-1);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setAlignment(Pos.CENTER);
        name.styleProperty().bind(Fonts.getFont(0.35, Fonts.DARK, Fonts.FontTyp.BOLD));
        name.setText(ResourceUtil.get(key,"fieldid"));

        this.getChildren().add(name);

    }

}
