package gui.gameboard;

import gui.buttons.FieldButton;
import javafx.scene.Group;
import model.Field;

public abstract class MapGUI extends Group {

    public abstract FieldButton getFieldButton(Field field);
}
