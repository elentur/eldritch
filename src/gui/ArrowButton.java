package gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ArrowButton extends StackPane {

    private final static Image backgroundImage = new Image("images/arrow.png");
    private BooleanProperty enabled = new SimpleBooleanProperty(true);
    private BooleanProperty armed = new SimpleBooleanProperty(false);

    private final Rectangle arrow;
    private Node actualNode;

    /*
    public DoubleProperty heightProperty() {
        return arrow.heightProperty();
    }
*/

    enum ArrowDir {
        UP, DOWN, LEFT, RIGHT
    }

    public ArrowButton(ArrowDir dir) {

        arrow = new Rectangle(50, 70, new ImagePattern(backgroundImage));
        if (dir.equals(ArrowDir.LEFT)) {
            arrow.setRotate(-90);
        } else if (dir.equals(ArrowDir.DOWN)) {
            arrow.setScaleY(-1);
        } else if (dir.equals(ArrowDir.RIGHT)) {
            arrow.setScaleY(-1);
            arrow.setRotate(-90);
        }

        actualNode = new Group(arrow);
        actualNode.setEffect(Effects.dropShadow);
        this.getChildren().add(actualNode);

        this.setOnMouseEntered(e -> arrow.setEffect(Effects.hover));
        this.setOnMouseExited(e -> {
            arrow.setEffect(null);
            if(!isDisabled()) {
                actualNode.setEffect(Effects.dropShadow);
            }
        });


        actualNode.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                actualNode.setEffect(null);
                setArmed(true);

            }
        });
        actualNode.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                actualNode.setEffect(Effects.dropShadow);
                setArmed(false);
            }
        });
        //this.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        this.setPadding(new Insets(12));
        this.setDisable(true);
        enabled.addListener(e -> {
            this.setDisable(!isEnabled());
            if (isDisabled()) {
                actualNode.setEffect(Effects.disabled);
            } else {
                actualNode.setEffect(Effects.dropShadow);
            }
        });
    }

    public BooleanProperty enabledProperty() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled.getValue();
    }

    public void setEnabled(boolean v) {
        enabled.setValue(v);
    }

    public boolean isArmed(){
        return armed.getValue();
    }
    public void setArmed(boolean v) {
        armed.setValue(v);
    }


}
