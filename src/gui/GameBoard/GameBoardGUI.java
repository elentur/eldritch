package gui.GameBoard;

import gui.buttons.Button;
import gui.buttons.InfoTextButton;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
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
