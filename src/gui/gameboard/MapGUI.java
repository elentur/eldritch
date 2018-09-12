package gui.gameboard;

import gui.buttons.FieldButton;
import javafx.scene.Group;
import javafx.scene.shape.SVGPath;
import model.Field;

import java.util.List;
import java.util.Map;

public abstract class MapGUI extends Group {

    public abstract FieldButton getFieldButton(Field field);

    public abstract void clearPath();

    public abstract void showPath(List<Field> path);
    public abstract Map<SVGPath,Boolean> getSvgPaths(List<Field> path);
}
