package gui.gameboard;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import model.GameBoard;


public class GameBoardGUI extends StackPane {


    public GameBoardGUI(GameBoard gameBoard) {

        WorldMapGUI map = new WorldMapGUI(gameBoard);
        Group zoomGroup = new Group();
        zoomGroup.getChildren().add(map);


        ZoomableScrollPane scrollPane = new ZoomableScrollPane(zoomGroup);
        scrollPane.getStyleClass().add("gameboard");
scrollPane.setPannable(true);
        this.getChildren().add(scrollPane);


    }
}
