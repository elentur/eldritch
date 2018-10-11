package gui.gameboard;

import gui.buttons.FieldButton;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import model.Field;
import model.GameBoard;


public class GameBoardGUI extends StackPane {
    private final static Image backgroundImage = new Image("images/gameBoard/BackgroundPattern.jpg",500,500,true,true,false);

    @Getter
    private final Group zoomGroup;
    @Getter
    private final ZoomableScrollPane scrollPane;
    @Getter
    private final WorldMapGUI map;

    public GameBoardGUI(GameBoard gameBoard) {

        map = new WorldMapGUI(gameBoard);
        Rectangle  background = new Rectangle(7724,4874);
        background.setFill(Color.TRANSPARENT);
        map.setTranslateX(960);
        map.setTranslateY(640);
        zoomGroup = new Group();
        zoomGroup.getChildren().addAll(background,map);


        scrollPane = new ZoomableScrollPane(zoomGroup);
        scrollPane.getStyleClass().add("gameboard");
        scrollPane.setPannable(true);
        this.getChildren().add(scrollPane);



    }

    public FieldButton getFieldButton(Field field) {
        for (Node node : zoomGroup.getChildren()) {
            if(node instanceof MapGUI) {
                FieldButton fieldButton = ((MapGUI) node).getFieldButton(field);
                if (fieldButton != null) return fieldButton;
            }
        }
        return null;
    }

}
