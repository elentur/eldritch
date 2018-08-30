package gui.gameboard;

import gui.buttons.FieldButton;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import model.Field;
import model.GameBoard;


public class GameBoardGUI extends StackPane {

    @Getter
    private final Group zoomGroup;
    @Getter
    private final ZoomableScrollPane scrollPane;
    @Getter
    private final WorldMapGUI map;

    public GameBoardGUI(GameBoard gameBoard) {

        map = new WorldMapGUI(gameBoard);
        zoomGroup = new Group();
        zoomGroup.getChildren().add(map);


        scrollPane = new ZoomableScrollPane(zoomGroup);
        scrollPane.getStyleClass().add("gameboard");
        scrollPane.setPannable(true);
        this.getChildren().add(scrollPane);


    }

    public FieldButton getFieldButton(Field field) {
        for (Node node : zoomGroup.getChildren()) {
            FieldButton fieldButton = ((MapGUI) node).getFieldButton(field);
            if (fieldButton != null) return fieldButton;
        }
        return null;
    }

}
