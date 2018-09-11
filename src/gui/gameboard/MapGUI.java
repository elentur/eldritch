package gui.gameboard;

import gui.buttons.FieldButton;
import javafx.scene.Group;
import model.Field;

import java.util.List;

public abstract class MapGUI extends Group {

    public abstract FieldButton getFieldButton(Field field);

    public abstract void clearPath();

    public abstract void showPath(List<Field> path);
}
